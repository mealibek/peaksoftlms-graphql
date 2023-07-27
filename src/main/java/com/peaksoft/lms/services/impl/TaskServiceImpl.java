package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.dto.requests.task.TaskRequest;
import com.peaksoft.lms.dto.responses.task.TaskResponse;
import com.peaksoft.lms.exceptions.NotFoundException;
import com.peaksoft.lms.models.Lesson;
import com.peaksoft.lms.models.Task;
import com.peaksoft.lms.repositories.LessonRepository;
import com.peaksoft.lms.repositories.TaskRepository;
import com.peaksoft.lms.repositories.custom.CustomTaskRepository;
import com.peaksoft.lms.services.TaskService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {

  private final TaskRepository repository;
  private final LessonRepository lessonRepository;
  private final CustomTaskRepository customTaskRepository;

  @Override
  public TaskResponse save(TaskRequest request) {
    Lesson lesson = lessonRepository.findById(request.getLessonId()).orElseThrow(
        () -> new NotFoundException(
            String.format("Lesson with id %s not found", request.getLessonId())
        )
    );

    Task newTask = Task.builder()
        .name(request.getName())
        .description(request.getHtmlContent())
        .lesson(lesson)
        .deadLine(request.getDeadLine())
        .build();

    repository.save(newTask);
    return TaskResponse.builder()
        .id(newTask.getId())
        .lessonId(newTask.getLesson().getId())
        .htmlContent(newTask.getDescription())
        .name(newTask.getName())
        .deadLine(newTask.getDeadLine())
        .build();
  }

  @Override
  public TaskResponse getById(Long id) {
    return customTaskRepository.getById(id).orElseThrow(
        () -> new NotFoundException(
            String.format("Task with id %s not found", id)
        )
    );
  }

  @Override
  public List<TaskResponse> getAll() {
    return customTaskRepository.getAll();
  }

  @Override
  public TaskResponse update(Long id, TaskRequest request) {
    Task task = repository.findById(id).orElseThrow(
        () -> new NotFoundException(
            String.format("Task with id %s not found", id)
        )
    );

    task.setName(request.getName());
    task.setDescription(request.getHtmlContent());
    task.setDeadLine(request.getDeadLine());

    repository.save(task);
    return TaskResponse.builder()
        .id(task.getId())
        .lessonId(task.getLesson().getId())
        .htmlContent(task.getDescription())
        .deadLine(task.getDeadLine())
        .name(task.getName())
        .build();
  }

  @Override
  public String delete(Long id) {
    Task task = repository.findById(id).orElseThrow(
        () -> new NotFoundException(
            String.format("Task with id %s not found", id)
        )
    );

    repository.delete(task);
    return "Task with id %s deleted successfully!";
  }
}
