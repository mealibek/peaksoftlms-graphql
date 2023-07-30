package com.peaksoft.lms.dto.responses.test;

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
public class OptionResponse {

  private Long id;
  private Integer order;
  private String name;
  private Boolean isCorrect;
}
