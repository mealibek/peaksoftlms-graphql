package com.peaksoft.lms.services;

import com.peaksoft.lms.dto.requests.auth.AuthRequest;
import com.peaksoft.lms.dto.requests.auth.ForgotRequest;
import com.peaksoft.lms.dto.requests.auth.ResetRequest;
import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import com.peaksoft.lms.dto.responses.excel.ExcelResponse;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface AccountService {
    AuthResponse signUp(AuthRequest request);
    AuthResponse signIn(AuthRequest request);
    AuthResponse forgotPassword(ForgotRequest request);
    AuthResponse resetPassword(ResetRequest request);
    List<ExcelResponse> exportStudentToExcel(HttpServletResponse response) throws IOException;

}
