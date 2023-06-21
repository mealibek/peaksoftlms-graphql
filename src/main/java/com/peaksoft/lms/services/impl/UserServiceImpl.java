package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.repositories.UserRepository;
import com.peaksoft.lms.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
}
