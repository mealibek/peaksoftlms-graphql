package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.QuestionRepository;
import com.peaksoft.lms.services.QuestionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository repository;
}
