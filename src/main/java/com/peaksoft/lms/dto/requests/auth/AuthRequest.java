package com.peaksoft.lms.dto.requests.auth;

import com.peaksoft.lms.validation.EmailValid;
import com.peaksoft.lms.validation.PasswordValid;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

  @EmailValid
  private String email;
  @PasswordValid
  private String password;
}
