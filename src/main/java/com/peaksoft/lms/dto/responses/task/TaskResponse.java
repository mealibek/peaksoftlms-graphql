package com.peaksoft.lms.dto.responses.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class TaskResponse {
  private Long id;
  private Long lessonId;
  private String name;
  private LocalDate deadLine;
  private String htmlContent;

}
