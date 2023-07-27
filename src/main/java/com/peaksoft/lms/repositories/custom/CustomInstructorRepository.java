package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.instructor.InstructorResponse;

import java.util.List;
import java.util.Optional;

public interface CustomInstructorRepository {
    List<InstructorResponse> getAllInstructors();
    Optional<InstructorResponse> getInstructorById(Long id);
}
