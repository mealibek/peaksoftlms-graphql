package com.peaksoft.lms.dto.requests.course;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {

  private String name;
  private String description;
  private LocalDate startDate;
}
