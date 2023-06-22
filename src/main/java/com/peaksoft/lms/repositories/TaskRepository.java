package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}