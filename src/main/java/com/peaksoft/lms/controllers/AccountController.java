package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.auth.AuthRequest;
import com.peaksoft.lms.dto.requests.auth.ForgotRequest;
import com.peaksoft.lms.dto.requests.auth.ResetRequest;
import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import com.peaksoft.lms.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

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
}