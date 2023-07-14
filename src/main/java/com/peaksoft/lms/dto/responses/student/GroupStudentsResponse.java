package com.peaksoft.lms.dto.responses.student;

import com.peaksoft.lms.enums.StudyFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupStudentsResponse {
    private Long id;
    private String fullName;
    private String groupName;
    private StudyFormat studyFormat;
    private String phoneNumber;
    private String email;
}
