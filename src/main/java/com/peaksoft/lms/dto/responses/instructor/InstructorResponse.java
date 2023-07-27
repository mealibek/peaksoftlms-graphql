package com.peaksoft.lms.dto.responses.instructor;

import com.peaksoft.lms.enums.Gender;
import com.peaksoft.lms.enums.StudyFormat;
import lombok.Builder;

@Builder
public record InstructorResponse(
        Long id,
        String fullName,
        StudyFormat specialization,
        String phoneNumber,
        Gender gender,
        String email,
        String password
) {
}
