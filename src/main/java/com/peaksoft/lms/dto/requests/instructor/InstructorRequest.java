package com.peaksoft.lms.dto.requests.instructor;

import com.peaksoft.lms.enums.Gender;
import com.peaksoft.lms.enums.StudyFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

@Builder
public record InstructorRequest(
        String firstName,
        String lastname,
        String phoneNumber,
        @Enumerated(EnumType.STRING)
        Gender gender,
        String email,
        String password,
        @Enumerated(EnumType.STRING)
        StudyFormat specialization
) {
}
