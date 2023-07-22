package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.lesson.LessonResponse;
import com.peaksoft.lms.dto.responses.lesson.LessonsResponse;

import java.util.List;
import java.util.Optional;

public interface CustomLessonRepository {
    List<LessonsResponse> getAll();
    Optional<LessonResponse> getById(Long id);
}
