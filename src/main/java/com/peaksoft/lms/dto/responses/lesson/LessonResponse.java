package com.peaksoft.lms.dto.responses.lesson;

import com.peaksoft.lms.dto.responses.presentation.PresentationResponse;
import com.peaksoft.lms.dto.responses.video.VideoLessonResponse;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonResponse {
    private Long id;
    private String name;
    private VideoLessonResponse videoLesson;
    private List<PresentationResponse> presentations;
}
