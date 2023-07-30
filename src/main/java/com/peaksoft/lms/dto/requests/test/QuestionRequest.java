package com.peaksoft.lms.dto.requests.test;

import com.peaksoft.lms.enums.OptionType;
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
public class QuestionRequest {
  private Integer order;
  private String name;
  private OptionType optionType;
  private List<OptionRequest> options;
}
