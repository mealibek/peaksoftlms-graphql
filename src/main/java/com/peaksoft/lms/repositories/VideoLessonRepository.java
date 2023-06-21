package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.VideoLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoLessonRepository extends JpaRepository<VideoLesson, Long> {
}