package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.FileRepository;
import com.peaksoft.lms.services.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileServiceImpl implements FileService {

  private final FileRepository repository;
}
