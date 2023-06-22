package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}