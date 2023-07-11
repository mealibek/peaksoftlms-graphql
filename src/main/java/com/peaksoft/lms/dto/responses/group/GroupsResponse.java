package com.peaksoft.lms.dto.responses.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class GroupsResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private LocalDate startDate;
    private LocalDate finishDate;
}
