package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.video.VideoLessonResponse;

import java.util.List;
import java.util.Optional;

public interface CustomVideoLessonRepository {
    List<VideoLessonResponse> getAll();

    Optional<VideoLessonResponse> getById(Long id);
}
