package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.TaskRepository;
import com.peaksoft.lms.services.TaskService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {

  private final TaskRepository repository;
}
