package com.peaksoft.lms.dto.requests.test;

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
public class OptionRequest {
  private String name;
  private Boolean isCorrect;
}
