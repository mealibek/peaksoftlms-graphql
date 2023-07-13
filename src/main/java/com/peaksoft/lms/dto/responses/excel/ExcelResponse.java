package com.peaksoft.lms.dto.responses.excel;

import com.peaksoft.lms.enums.StudyFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ExcelResponse {
    private Long id;
    private String fullName;
    private String group;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private String phoneNumber;
    private String email;
}
