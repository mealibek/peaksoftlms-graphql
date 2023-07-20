package com.peaksoft.lms.dto.requests.group;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest {
    @NotNull(message = "Name must not be empty !")
    private String name;
    @NotNull(message = "Description must not be empty!")
    private String description;
    @NotNull(message = "Image must not be empty!")
    private String image;
    private LocalDate startDate;

}
