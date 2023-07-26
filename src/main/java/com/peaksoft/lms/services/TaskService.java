package com.peaksoft.lms.services;

import com.peaksoft.lms.dto.requests.task.TaskRequest;
import com.peaksoft.lms.dto.responses.task.TaskResponse;
import java.util.List;
import java.util.Optional;

public interface TaskService {
  TaskResponse save(TaskRequest request);
  TaskResponse getById(Long id);
  List<TaskResponse> getAll();
  TaskResponse update(Long id,TaskRequest request);
  String delete(Long id);
}
