package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.dto.requests.presentation.PresentationRequest;
import com.peaksoft.lms.dto.responses.presentation.PresentationResponse;
import com.peaksoft.lms.exceptions.NotFoundException;
import com.peaksoft.lms.models.Lesson;
import com.peaksoft.lms.models.Presentation;
import com.peaksoft.lms.repositories.LessonRepository;
import com.peaksoft.lms.repositories.PresentationRepository;
import com.peaksoft.lms.repositories.custom.CustomPresentationRepository;
import com.peaksoft.lms.services.PresentationService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PresentationServiceImpl implements PresentationService {

  private final PresentationRepository repository;
  private final LessonRepository lessonRepository;
  private final CustomPresentationRepository customPresentationRepository;

  @Override
  public PresentationResponse save(PresentationRequest request) {
    Lesson lesson = lessonRepository.findById(request.getLessonId()).orElseThrow(
        () -> new NotFoundException(
            String.format("Lesson with id %s not found!", request.getLessonId())
        )
    );

    Presentation presentation = Presentation.builder()
        .lesson(lesson)
        .name(request.getName())
        .description(request.getDescription())
        .formatPPT(request.getFormatPPT())
        .build();

    repository.save(presentation);
    return PresentationResponse.builder()
        .id(presentation.getId())
        .lessonId(presentation.getLesson().getId())
        .name(presentation.getName())
        .description(presentation.getDescription())
        .formatPPT(presentation.getFormatPPT())
        .build();
  }

  @Override
  public PresentationResponse update(Long id, PresentationRequest request) {
    Lesson lesson = lessonRepository.findById(request.getLessonId()).orElseThrow(
        () -> new NotFoundException(
            String.format("Lesson with id %s not found!", request.getLessonId())
        )
    );

    Presentation presentation = repository.findById(id).orElseThrow(
        () -> new NotFoundException(
            String.format("Presentation with id %s not found!", id)
        )
    );

    presentation.setName(request.getName());
    presentation.setDescription(request.getDescription());
    presentation.setFormatPPT(request.getFormatPPT());

    repository.save(presentation);
    return PresentationResponse.builder()
        .id(presentation.getId())
        .lessonId(presentation.getLesson().getId())
        .name(presentation.getName())
        .description(presentation.getDescription())
        .formatPPT(presentation.getFormatPPT())
        .build();
  }

  @Override
  public PresentationResponse getById(Long id) {
    return customPresentationRepository.getById(id).orElseThrow(
        () -> new NotFoundException(
            String.format("Presentation with id %s not found!", id)
        )
    );
  }

  @Override
  public List<PresentationResponse> getAll() {
    return customPresentationRepository.getAll();
  }

  @Override
  public String delete(Long id) {
    Presentation presentation = repository.findById(id).orElseThrow(
        () -> new NotFoundException(
            String.format("Presentation with id %s not found!", id)
        )
    );
    repository.delete(presentation);
    return "Presentation was deleted successfully!";
  }
}
