package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.LessonRepository;
import com.peaksoft.lms.services.LessonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LessonServiceImpl implements LessonService {

  private final LessonRepository repository;
}
