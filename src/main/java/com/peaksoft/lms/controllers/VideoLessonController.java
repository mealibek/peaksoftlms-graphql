package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.video.VideoLessonRequest;
import com.peaksoft.lms.dto.responses.video.VideoLessonResponse;
import com.peaksoft.lms.services.VideoLessonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@RequiredArgsConstructor
@Validated
public class VideoLessonController {

  private final VideoLessonService videoLessonService;

  @MutationMapping(name = "saveVideoLesson")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public VideoLessonResponse saveVideoLesson(@Argument VideoLessonRequest request) {
    return videoLessonService.save(request);
  }

  @MutationMapping(name = "updateVideoLesson")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public VideoLessonResponse update(@Argument Long id, @Argument VideoLessonRequest request) {
    return videoLessonService.update(id, request);
  }

  @QueryMapping(name = "getVideoLessons")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
  public List<VideoLessonResponse> getVideoLessons() {
    return videoLessonService.getAll();
  }

  @QueryMapping(name = "getVideoLesson")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
  public VideoLessonResponse getVideoLesson(@Argument Long id) {
    return videoLessonService.getById(id);
  }

  @MutationMapping(name = "deleteVideoLesson")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public String deleteVideoLesson(@Argument Long id) {
    return videoLessonService.delete(id);
  }

}
