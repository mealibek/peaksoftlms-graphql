package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.task.TaskResponse;
import java.util.List;
import java.util.Optional;

public interface CustomTaskRepository {
  List<TaskResponse> getAll();
  Optional<TaskResponse> getById(Long id);
}
