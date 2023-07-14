package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.course.CourseResponse;

import java.util.List;
import java.util.Optional;

public interface CustomCourseRepository {
    List<CourseResponse> getAll();
    Optional<CourseResponse> getById(Long id);
}
