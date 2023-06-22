package com.peaksoft.lms.repositories;

import com.peaksoft.lms.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}