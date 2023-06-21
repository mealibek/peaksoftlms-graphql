package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.OptionRepository;
import com.peaksoft.lms.services.OptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OptionServiceImpl implements OptionService {
   private final OptionRepository repository;
}
