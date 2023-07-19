package com.peaksoft.lms.services;

import com.peaksoft.lms.dto.requests.video.VideoLessonRequest;
import com.peaksoft.lms.dto.responses.video.VideoLessonResponse;
import java.util.List;

public interface VideoLessonService {
  VideoLessonResponse save(VideoLessonRequest request);
  VideoLessonResponse update(Long id,VideoLessonRequest request);
  VideoLessonResponse getById(Long id);
  List<VideoLessonResponse> getAll();
  String delete(Long id);
}
