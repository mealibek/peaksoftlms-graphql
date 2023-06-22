package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
}