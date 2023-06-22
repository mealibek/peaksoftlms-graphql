package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.VideoLessonRepository;
import com.peaksoft.lms.services.VideoLessonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VideoLessonServiceImpl implements VideoLessonService {
    private final VideoLessonRepository repository;
}
