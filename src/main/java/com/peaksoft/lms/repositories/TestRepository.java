package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}