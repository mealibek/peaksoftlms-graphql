package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.config.jwt.JwtService;
import com.peaksoft.lms.dto.requests.auth.AuthRequest;
import com.peaksoft.lms.dto.requests.auth.ForgotRequest;
import com.peaksoft.lms.dto.requests.auth.ResetRequest;
import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import com.peaksoft.lms.dto.responses.excel.ExcelResponse;
import com.peaksoft.lms.enums.Role;
import com.peaksoft.lms.exceptions.AlreadyExistException;
import com.peaksoft.lms.exceptions.BadRequestException;
import com.peaksoft.lms.exceptions.NotFoundException;
import com.peaksoft.lms.models.Account;
import com.peaksoft.lms.models.User;
import com.peaksoft.lms.repositories.AccountRepository;
import com.peaksoft.lms.repositories.custom.CustomExcelRepository;
import com.peaksoft.lms.services.AccountService;
import com.peaksoft.lms.services.EmailService;
import com.peaksoft.lms.utils.ExportToExcel;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;
    private final CustomExcelRepository customExcelRepository;

    @Override
    public AuthResponse signUp(AuthRequest request) {
        boolean user = repository.existsAccountByEmail(request.getEmail());
        if (user) throw new AlreadyExistException(
                String.format("User with email %s already exists", request.getEmail())
        );

        String encodedPass = passwordEncoder.encode(request.getPassword());
        Account newAccount = Account.builder()
                .email(request.getEmail())
                .password(encodedPass)
                .role(Role.STUDENT)
                .build();
        repository.save(newAccount);
        String jwtToken = jwtService.generateToken(newAccount);
        return AuthResponse
                .builder()
                .email(newAccount.getUsername())
                .token(jwtToken)
                .role(newAccount.getRole())
                .build();
    }

    @Override
    public AuthResponse signIn(AuthRequest request) {
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException(
                        String.format("Email %s is not registered!", request.getEmail())
                ));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse forgotPassword(ForgotRequest request) {

        String resetToken = UUID.randomUUID().toString();

        Account account = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("User with email %s not found.".formatted(request.getEmail())));
        account.setResetPasswordToken(resetToken);
        repository.save(account);

        User user = account.getUser();
        String userLastName = "";
        String userFirstName = "";
        if (user != null) {
            userLastName = Optional.ofNullable(user.getLastName()).orElse(" ");
            userFirstName = Optional.ofNullable(user.getFirstName()).orElse(" ");
        }

        String resetPasswordLink = request.getLinkResetPassword() + "/" + resetToken;
        String subject = "Password Reset Request";
        Context context = new Context();
        context.setVariable("message", "Hello, " + userLastName + " " + userFirstName + "!");
        context.setVariable("link", resetPasswordLink);

        String htmlContent = templateEngine.process("reset-password-template.html", context);

        emailService.sendEmail(request.getEmail(), subject, htmlContent);
        return AuthResponse.builder()
                .email(account.getEmail())
                .role(account.getRole())
                .build();
    }

    @Override
    public AuthResponse resetPassword(ResetRequest request) {
        Account account = repository.findByResetPasswordToken(request.getResetPasswordToken())
                .orElseThrow(() -> new NotFoundException("Account with reset password token %s not found."
                        .formatted(request.getConfirmPassword())));

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Sorry, the entered passwords do not match. " +
                    "Please make sure that the new password and its confirmation match exactly and try again.");
        }

        String encodedPassword = passwordEncoder.encode(request.getNewPassword());
        account.setPassword(encodedPassword);
        account.setResetPasswordToken(null);
        repository.save(account);

        return AuthResponse.builder()
                .email(account.getEmail())
                .role(account.getRole())
                .build();
    }

    @Override
    public List<ExcelResponse> exportStudentToExcel(HttpServletResponse response) throws IOException {
        List<ExcelResponse> students = customExcelRepository.getAllExportExcelStudents();
        ExportToExcel exportToExcel = new ExportToExcel(students);
        exportToExcel.exportDataToExcel(response);
        return students;
    }

}
