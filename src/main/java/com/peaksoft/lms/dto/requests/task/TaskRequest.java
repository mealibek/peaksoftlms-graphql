package com.peaksoft.lms.dto.requests.task;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class TaskRequest {
  private Long lessonId;
  @NotNull(message = "Task name can't be null")
  private String name;
  @NotNull(message = "Task deadLine can't be null")
  private LocalDate deadLine;
  @NotNull(message = "Task HTML Content can't be null")
  private String htmlContent;
}
