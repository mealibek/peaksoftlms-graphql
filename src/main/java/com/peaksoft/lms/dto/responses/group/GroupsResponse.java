package com.peaksoft.lms.dto.responses.group;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
public class GroupsResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private LocalDate startDate;
    private LocalDate finishDate;

    public GroupsResponse(Long id, String name, String description, String image, LocalDate startDate, LocalDate finishDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}
