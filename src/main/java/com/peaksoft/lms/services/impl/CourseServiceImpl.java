package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.dto.requests.course.CourseRequest;
import com.peaksoft.lms.dto.responses.course.CourseResponse;
import com.peaksoft.lms.exceptions.AlreadyExistException;
import com.peaksoft.lms.models.Course;
import com.peaksoft.lms.repositories.CourseRepository;
import com.peaksoft.lms.repositories.custom.CustomCourseRepository;
import com.peaksoft.lms.services.CourseService;
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
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;
    private final CustomCourseRepository customRepository;

    @Override
    public CourseResponse save(CourseRequest request) {
        Boolean existingCourse = repository.existsCoursesByName(request.getName());
        if (existingCourse) throw new AlreadyExistException(
                String.format("Sorry, Course with a name %s ALREADY EXISTS", request.getName())
        );

        Course newCourse = Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .build();

        repository.save(newCourse);
        return CourseResponse.builder()
                .id(newCourse.getId())
                .name(newCourse.getName())
                .description(newCourse.getDescription())
                .startDate(newCourse.getStartDate())
                .build();
    }

    @Override
    public List<CourseResponse> getAll() {
        return customRepository.getAll();
    }

    @Override
    public CourseResponse getById(Long id) {
        return null;
    }

    @Override
    public CourseResponse update(Long id, CourseRequest request) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }
}
