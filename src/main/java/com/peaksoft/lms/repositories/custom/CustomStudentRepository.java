package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.student.StudentsResponse;

import java.util.List;
import java.util.Optional;

public interface CustomStudentRepository {
    List<StudentsResponse> getAllStudents();
    Optional<StudentsResponse> getStudentById(Long id);
}
