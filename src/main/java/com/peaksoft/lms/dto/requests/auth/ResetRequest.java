package com.peaksoft.lms.dto.requests.auth;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetRequest {
    private String resetPasswordToken;
    private String newPassword;
    private String confirmPassword;
}
