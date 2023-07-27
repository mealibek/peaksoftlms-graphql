package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.auth.AuthRequest;
import com.peaksoft.lms.dto.requests.auth.ForgotRequest;
import com.peaksoft.lms.dto.requests.auth.ResetRequest;
import com.peaksoft.lms.dto.requests.instructor.InstructorRequest;
import com.peaksoft.lms.dto.requests.student.StudentRequest;
import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import com.peaksoft.lms.dto.responses.instructor.InstructorResponse;
import com.peaksoft.lms.dto.responses.student.StudentsResponse;
import com.peaksoft.lms.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class AccountController {
    private final AccountService accountService;

    @MutationMapping(name = "signUp")
    public AuthResponse signUp(@Argument @Valid AuthRequest request) {
        return accountService.signUp(request);
    }

    @MutationMapping(name = "signIn")
    public AuthResponse signIn(@Argument @Valid AuthRequest request) {
        return accountService.signIn(request);
    }

    @MutationMapping
    public AuthResponse forgotPassword(@Argument @Valid ForgotRequest request) {
        return accountService.forgotPassword(request);
    }

    @MutationMapping
    public AuthResponse resetPassword(@Argument @Valid ResetRequest request) {
        return accountService.resetPassword(request);
    }

    // TODO Students CRUD

    @PreAuthorize("hasAuthority('ADMIN')")
    @MutationMapping(name = "saveStudent")
    public StudentsResponse save(@Argument @Valid StudentRequest request) {
        return accountService.saveStudent(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @QueryMapping(name = "getStudents")
    public List<StudentsResponse> getStudents() {
        return accountService.getAllStudents();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
    @QueryMapping(name = "getStudent")
    public StudentsResponse getStudentById(@Argument Long id) {
        return accountService.getStudentById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @MutationMapping(name = "updateStudent")
    public StudentsResponse updateStudent(@Argument @Valid StudentRequest request, @Argument Long id) {
        return accountService.updateStudent(id, request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @MutationMapping(name = "deleteStudent")
    public String deleteStudent(@Argument Long id) {
        return accountService.deleteStudent(id);
    }

    // TODO Instructor CRUD

    @PreAuthorize("hasAuthority('ADMIN')")
    @MutationMapping(name = "saveInstructor")
    public InstructorResponse saveInstructor(@Argument @Valid InstructorRequest request) {
        return accountService.saveInstructor(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @QueryMapping(name = "getInstructors")
    public List<InstructorResponse> getInstructors() {
        return accountService.getAllInstructors();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
    @QueryMapping(name = "getInstructor")
    public InstructorResponse getInstructorById(@Argument Long id) {
        return accountService.getInstructorById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @MutationMapping(name = "updateInstructor")
    public InstructorResponse updateInstructor(@Argument @Valid InstructorRequest request, @Argument Long id) {
        return accountService.updateInstructor(id, request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @MutationMapping(name = "deleteInstructor")
    public String deleteInstructor(@Argument Long id) {
        return accountService.deleteInstructor(id);
    }
}