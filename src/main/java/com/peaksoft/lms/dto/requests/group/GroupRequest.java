package com.peaksoft.lms.dto.requests.group;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {
    private String name;
    private String description;
    private String image;
    private LocalDate startDate;

}
