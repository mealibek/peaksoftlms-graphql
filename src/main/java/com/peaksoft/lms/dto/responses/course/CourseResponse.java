package com.peaksoft.lms.dto.responses.course;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {

  private Long id;
  private String name;
  private String description;
  private LocalDate startDate;
}