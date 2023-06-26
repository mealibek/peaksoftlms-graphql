package com.peaksoft.lms.dto.requests.auth;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotRequest {
    private String email;
    private String linkResetPassword;
}
