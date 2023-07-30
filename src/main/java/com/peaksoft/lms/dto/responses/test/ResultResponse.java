package com.peaksoft.lms.dto.responses.test;

import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponse {

  private Long id;
  private Long testId;
  private Integer correctAnswers;
  private Integer inCorrectAnswers;
  private Float score;
  private AuthResponse account;

}
