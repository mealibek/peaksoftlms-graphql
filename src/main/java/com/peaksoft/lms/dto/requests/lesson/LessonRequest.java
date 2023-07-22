package com.peaksoft.lms.dto.requests.lesson;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {
    @NotNull(message = "Lesson name must not be empty !")
    private String name;
}
