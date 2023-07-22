package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.course.CourseRequest;
import com.peaksoft.lms.dto.responses.course.CourseResponse;
import com.peaksoft.lms.services.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class CourseController {

  private final CourseService courseService;

  @MutationMapping(name = "saveCourse")
  @PreAuthorize("hasAuthority('ADMIN')")
  public CourseResponse saveCourse(@Argument @Valid CourseRequest request) {
    return courseService.save(request);
  }

  @QueryMapping(name = "getCourses")
  @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT','INSTRUCTOR')")
  public List<CourseResponse> getCourses() {
    return courseService.getAll();
  }

  @QueryMapping(name = "getCourse")
  @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT','INSTRUCTOR')")
  public CourseResponse getCourseById(@Argument Long id) {
    return courseService.getById(id);
  }

  @MutationMapping(name = "updateCourse")
  @PreAuthorize("hasAuthority('ADMIN')")
  public CourseResponse updateCourseById(@Argument Long id,
      @Argument @Valid CourseRequest request) {
    return courseService.update(id, request);
  }

  @MutationMapping(name = "deleteCourse")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String deleteCourseById(@Argument Long id) {
    return courseService.delete(id);
  }
}
