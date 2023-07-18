package com.peaksoft.lms.dto.requests.auth;

import com.peaksoft.lms.validation.EmailValid;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForgotRequest {

  @EmailValid
  private String email;
  private String linkResetPassword;
}
