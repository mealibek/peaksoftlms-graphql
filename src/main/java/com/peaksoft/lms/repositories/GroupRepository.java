package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}