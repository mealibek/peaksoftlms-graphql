package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.task.TaskRequest;
import com.peaksoft.lms.dto.responses.task.TaskResponse;
import com.peaksoft.lms.services.TaskService;
import jakarta.validation.Valid;
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
public class TaskController {

  private final TaskService taskService;

  @MutationMapping(name = "saveTask")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public TaskResponse saveTask(@Argument @Valid TaskRequest request) {
    return taskService.save(request);
  }

  @QueryMapping(name = "getTasks")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
  public List<TaskResponse> getAll() {
    return taskService.getAll();
  }

  @QueryMapping(name = "getTask")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
  public TaskResponse getTask(@Argument Long id) {
    return taskService.getById(id);
  }

  @MutationMapping(name = "updateTask")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public TaskResponse updateTask(@Argument Long id, @Argument TaskRequest request) {
    return taskService.update(id, request);
  }

  @MutationMapping(name = "deleteTask")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public String deleteTask(@Argument Long id) {
    return taskService.delete(id);
  }
}
