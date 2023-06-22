package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}