package com.peaksoft.lms.controllers;

import com.peaksoft.lms.services.AccountService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExcelController {
    private final AccountService accountService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Student_Information.xlsx";
        response.setHeader(headerKey, headerValue);
        accountService.exportStudentToExcel(response);
    }
}
