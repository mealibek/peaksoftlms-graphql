package com.peaksoft.lms.dto.responses.group;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
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
