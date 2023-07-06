package com.peaksoft.lms.dto.requests.auth;


import com.peaksoft.lms.validation.PasswordValid;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetRequest {
    private String resetPasswordToken;
    @PasswordValid
    private String newPassword;
    @PasswordValid
    private String confirmPassword;
}
