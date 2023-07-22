package com.peaksoft.lms.services;

import com.peaksoft.lms.dto.requests.lesson.LessonRequest;
import com.peaksoft.lms.dto.responses.lesson.LessonResponse;
import com.peaksoft.lms.dto.responses.lesson.LessonsResponse;

import java.util.List;

public interface LessonService {
    LessonResponse save(LessonRequest request);
    List<LessonsResponse> getAll();
    LessonResponse getById(Long id);
    LessonResponse update(LessonRequest request,Long id);
    String delete(Long id);
}
