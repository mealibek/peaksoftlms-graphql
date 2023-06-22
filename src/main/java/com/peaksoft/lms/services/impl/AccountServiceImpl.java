package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.config.jwt.JwtService;
import com.peaksoft.lms.dto.requests.auth.AuthRequest;
import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import com.peaksoft.lms.enums.Role;
import com.peaksoft.lms.models.Account;
import com.peaksoft.lms.repositories.AccountRepository;
import com.peaksoft.lms.services.AccountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signUp(AuthRequest request) {
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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }

    @Override
    public List<Account> get() {
        return repository.findAll();
    }
}
