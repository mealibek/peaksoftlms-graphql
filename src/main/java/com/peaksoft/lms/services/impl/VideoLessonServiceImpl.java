package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.dto.requests.video.VideoLessonRequest;
import com.peaksoft.lms.dto.responses.video.VideoLessonResponse;
import com.peaksoft.lms.enums.FileType;
import com.peaksoft.lms.exceptions.NotFoundException;
import com.peaksoft.lms.models.File;
import com.peaksoft.lms.models.Lesson;
import com.peaksoft.lms.models.VideoLesson;
import com.peaksoft.lms.repositories.LessonRepository;
import com.peaksoft.lms.repositories.VideoLessonRepository;
import com.peaksoft.lms.repositories.custom.CustomVideoLessonRepository;
import com.peaksoft.lms.services.VideoLessonService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VideoLessonServiceImpl implements VideoLessonService {

  private final VideoLessonRepository repository;
  private final LessonRepository lessonRepository;
  private final CustomVideoLessonRepository customVideoLessonRepository;

  @Override
  public VideoLessonResponse save(VideoLessonRequest request) {
    Lesson lesson = lessonRepository.findById(request.getLessonId()).orElseThrow(
        () -> new NotFoundException(
            String.format("Lesson with id %s not found!", request.getLessonId()))
    );

    VideoLesson videoLesson = VideoLesson.builder()
        .lesson(lesson)
        .name(request.getName())
        .description(request.getDescription())
        .build();

    File file = File.builder()
        .fileType(FileType.VIDEO)
        .url(request.getUrl())
        .build();
    videoLesson.setFile(file);

    repository.save(videoLesson);
    return VideoLessonResponse.builder()
        .id(videoLesson.getId())
        .name(videoLesson.getName())
        .url(videoLesson.getFile().getUrl())
        .description(videoLesson.getDescription())
        .lessonId(videoLesson.getLesson().getId())
        .build();
  }

  @Override
  public VideoLessonResponse update(Long id, VideoLessonRequest request) {
    VideoLesson videoLesson = repository.findById(id).orElseThrow(
        () -> new NotFoundException(
            String.format("VideoLesson with id %s not found!", id)
        )
    );
    videoLesson.setName(request.getName());
    videoLesson.setDescription(request.getDescription());
    videoLesson.getFile().setUrl(request.getUrl());

    repository.save(videoLesson);
    return VideoLessonResponse.builder()
        .id(videoLesson.getId())
        .name(videoLesson.getName())
        .description(videoLesson.getDescription())
        .lessonId(videoLesson.getLesson().getId())
        .url(videoLesson.getFile().getUrl())
        .build();
  }

  @Override
  public VideoLessonResponse getById(Long id) {
    return customVideoLessonRepository.getById(id).orElseThrow(
        () -> new NotFoundException(
            String.format("VideoLesson with id %s not found!", id)
        )
    );
  }

  @Override
  public List<VideoLessonResponse> getAll() {
    return customVideoLessonRepository.getAll();
  }

  @Override
  public String delete(Long id) {
    VideoLesson videoLesson = repository.findById(id).orElseThrow(
        () -> new NotFoundException(
            String.format("VideoLesson with id %s not found!", id)
        )
    );

    repository.delete(videoLesson);
    return "VideoLesson deleted successfully!";
  }
}
