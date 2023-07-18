package com.peaksoft.lms.services;

import com.peaksoft.lms.dto.requests.course.CourseRequest;
import com.peaksoft.lms.dto.responses.course.CourseResponse;

import java.util.List;

public interface CourseService {

  CourseResponse save(CourseRequest request);

  List<CourseResponse> getAll();

  CourseResponse getById(Long id);

  CourseResponse update(Long id, CourseRequest request);

  String delete(Long id);
}
