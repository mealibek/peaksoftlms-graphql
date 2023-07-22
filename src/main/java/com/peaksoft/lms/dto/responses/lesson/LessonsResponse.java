package com.peaksoft.lms.dto.responses.lesson;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonsResponse {
    private Long id;
    private String name;
    private String videoLesson;
    private String presentation;
    private String task;
}
