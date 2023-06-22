package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.auth.AuthRequest;
import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import com.peaksoft.lms.models.Account;
import com.peaksoft.lms.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @MutationMapping
    public AuthResponse signUp(@Argument AuthRequest request) {
        return accountService.signUp(request);
    }

    @QueryMapping
    public List<Account> get(){
        return accountService.get();
    }
}
