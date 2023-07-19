package com.peaksoft.lms.dto.requests.video;

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
public class VideoLessonRequest {
  @NotNull(message = "LessonId can't be null")
  @Positive(message = "LessonId can't be negative")
  private Long lessonId;
  @NotNull(message = "VideoLesson name can't be null")
  private String name;
  @NotNull(message = "VideoLesson description can't be null")
  private String description;
  @NotNull(message = "VideoLesson url can't be null")
  private String url;
}
