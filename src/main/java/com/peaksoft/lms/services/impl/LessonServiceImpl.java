package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.dto.requests.lesson.LessonRequest;
import com.peaksoft.lms.dto.responses.lesson.LessonResponse;
import com.peaksoft.lms.dto.responses.lesson.LessonsResponse;
import com.peaksoft.lms.exceptions.NotFoundException;
import com.peaksoft.lms.models.Lesson;
import com.peaksoft.lms.repositories.LessonRepository;
import com.peaksoft.lms.repositories.custom.CustomLessonRepository;
import com.peaksoft.lms.services.LessonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;
    private final CustomLessonRepository customLessonRepository;

    @Override
    public LessonResponse save(LessonRequest request) {
        Lesson lesson = Lesson.builder()
                .name(request.getName())
                .build();

        repository.save(lesson);
        return LessonResponse.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .videoLesson(null)
                .presentations(new ArrayList<>())
                .build();
    }

    @Override
    public List<LessonsResponse> getAll() {
        return customLessonRepository.getAll();
    }

    @Override
    public LessonResponse getById(Long id) {
        return customLessonRepository.getById(id).orElseThrow(() ->
                new NotFoundException(String.format("Lesson with id : %s not found !", id)));
    }

    @Override
    public LessonResponse update(LessonRequest request, Long id) {
        Lesson lesson = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Lesson with id: %s  not found !", id)));

        lesson.setName(request.getName() != null ? request.getName() : lesson.getName());
        repository.save(lesson);

        return LessonResponse.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .videoLesson(null)
                .presentations(new ArrayList<>())
                .build();
    }

    @Override
    public String delete(Long id) {
        Lesson lesson = repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Lesson with id: %s  not found !", id)));
        repository.delete(lesson);
        return String.format("Lesson with id : %s successfully deleted !", id);
    }
}
