package com.peaksoft.lms.dto.requests.test;

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
public class TestRequest {
  private Long lessonId;
  private String name;
  private List<QuestionRequest> questions;
}
