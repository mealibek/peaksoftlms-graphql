package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Optional<Result> findByAccount_Id(Long aLong);
}