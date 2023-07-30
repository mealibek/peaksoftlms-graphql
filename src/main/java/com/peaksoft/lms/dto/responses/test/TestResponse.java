package com.peaksoft.lms.dto.responses.test;

import com.peaksoft.lms.dto.responses.auth.AuthResponse;
import java.util.List;
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
public class TestResponse {
  private Long id;
  private Long lessonId;
  private String name;
  private List<QuestionResponse> questions;
  private Boolean isTaken;
  private ResultResponse result;
  private AuthResponse account;
}
