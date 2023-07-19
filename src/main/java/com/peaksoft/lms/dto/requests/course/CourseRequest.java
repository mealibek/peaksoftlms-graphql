package com.peaksoft.lms.dto.requests.course;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    @NotNull(message = "Name must not be empty!")
    private String name;
    @NotNull(message = "Description not be empty!")
    private String description;
    private LocalDate startDate;
}
