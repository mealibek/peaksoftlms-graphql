package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
}