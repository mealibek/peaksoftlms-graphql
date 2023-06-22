package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}