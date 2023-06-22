package com.peaksoft.lms.services;

import com.peaksoft.lms.dto.requests.auth.AuthRequest;
import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import com.peaksoft.lms.models.Account;

import java.util.List;

public interface AccountService {
    AuthResponse signUp(AuthRequest request);
    AuthResponse signIn(AuthRequest request);
    List<Account> get();
}
