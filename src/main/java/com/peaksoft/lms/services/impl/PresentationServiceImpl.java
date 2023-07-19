package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.PresentationRepository;
import com.peaksoft.lms.services.PresentationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PresentationServiceImpl implements PresentationService {

  private final PresentationRepository repository;
}
