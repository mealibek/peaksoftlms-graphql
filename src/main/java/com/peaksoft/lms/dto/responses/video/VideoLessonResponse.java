package com.peaksoft.lms.dto.responses.video;

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
public class VideoLessonResponse {
  private Long id;
  private String name;
  private String description;
  private String url;
  private Long lessonId;
}
