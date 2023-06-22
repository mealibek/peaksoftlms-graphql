package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.GroupRepository;
import com.peaksoft.lms.services.GroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GroupServiceImpl implements GroupService {
    private final GroupRepository repository;
}
