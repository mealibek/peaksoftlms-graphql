package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.ResultRepository;
import com.peaksoft.lms.services.ResultService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ResultServiceImpl implements ResultService {

  private final ResultRepository repository;
}
