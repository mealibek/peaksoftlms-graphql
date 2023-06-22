package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}