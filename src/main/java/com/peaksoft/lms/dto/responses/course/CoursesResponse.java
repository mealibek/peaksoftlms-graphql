package com.peaksoft.lms.dto.responses.course;

import java.time.LocalDate;
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
public class CoursesResponse {
  private Long id;
  private String name;
  private String description;
  private LocalDate startDate;
  private String imageUrl;
}
