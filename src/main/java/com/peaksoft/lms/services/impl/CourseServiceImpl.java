package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.CourseRepository;
import com.peaksoft.lms.services.CourseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CourseServiceImpl implements CourseService {
    private final CourseRepository repository;
}
