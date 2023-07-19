package com.peaksoft.lms.dto.requests.presentation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class PresentationRequest {
  @Positive(message = "LessonId can't be negative")
  private Long lessonId;
  @NotNull(message = "Presentation name can't be null")
  private String name;
  @NotNull(message = "Presentation description can't be null")
  private String description;
  @NotNull(message = "Presentation formatPPT can't be null")
  private String formatPPT;
}
