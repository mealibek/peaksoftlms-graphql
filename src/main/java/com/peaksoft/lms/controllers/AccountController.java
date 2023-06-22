package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.auth.AuthRequest;
import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import com.peaksoft.lms.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @MutationMapping(name = "signUp")
    public AuthResponse signUp(@Argument AuthRequest request) {
        return accountService.signUp(request);
    }

    @MutationMapping(name = "signIn")
    public AuthResponse signIn(@Argument AuthRequest request){
        return accountService.signIn(request);
    }
}
