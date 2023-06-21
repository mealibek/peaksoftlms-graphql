package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}