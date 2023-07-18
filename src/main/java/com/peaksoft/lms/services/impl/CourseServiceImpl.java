package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.dto.requests.course.CourseRequest;
import com.peaksoft.lms.dto.responses.course.CourseResponse;
import com.peaksoft.lms.exceptions.AlreadyExistException;
import com.peaksoft.lms.exceptions.NotFoundException;
import com.peaksoft.lms.models.Course;
import com.peaksoft.lms.repositories.CourseRepository;
import com.peaksoft.lms.repositories.custom.impl.CustomCourseRepositoryImpl;
import com.peaksoft.lms.services.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;
    private final CustomCourseRepositoryImpl customRepository;

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
        return customRepository.getById(id).orElseThrow(() -> new NotFoundException("Course with id %s not found !".formatted(id)));
    }

    @Override
    public CourseResponse update(Long id, CourseRequest request) {
        Course course = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found!")
        );

        if(request.getName() != null)
            course.setName(request.getName());
        if(request.getDescription() != null)
            course.setDescription(request.getDescription());
        if(request.getStartDate() != null)
            course.setStartDate(request.getStartDate());

        repository.save(course);
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .startDate(course.getStartDate())
                .build();
    }

    @Override
    public String delete(Long id) {
        Course course = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Course not found!")
        );
        course.getInstructors().forEach(x -> x.setCourse(null));
        course.getGroups().forEach(g -> g.setCourse(null));
        repository.delete(course);
        return "Course deleted successfully!";
    }
}
