package com.peaksoft.lms.dto.responses.presentation;

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
public class PresentationResponse {
    private Long id;
    private Long lessonId;
    private String name;
    private String description;
    private String formatPPT;
}
