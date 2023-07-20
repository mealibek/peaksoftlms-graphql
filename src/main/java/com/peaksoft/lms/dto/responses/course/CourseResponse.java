package com.peaksoft.lms.dto.responses.course;

import com.peaksoft.lms.dto.responses.student.StudentsResponse;

import java.util.List;

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
    private String imageUrl;
    private List<StudentsResponse> students;
}