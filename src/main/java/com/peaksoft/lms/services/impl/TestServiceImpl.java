package com.peaksoft.lms.services.impl;

import com.peaksoft.lms.dto.requests.test.TestRequest;
import com.peaksoft.lms.dto.responses.test.TestResponse;
import com.peaksoft.lms.exceptions.NotFoundException;
import com.peaksoft.lms.models.Account;
import com.peaksoft.lms.models.Test;
import com.peaksoft.lms.repositories.TestRepository;
import com.peaksoft.lms.repositories.custom.CustomTestRepository;
import com.peaksoft.lms.services.TestService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TestServiceImpl implements TestService {

  private final TestRepository repository;
  private final CustomTestRepository customTestRepository;

  @Override
  public TestResponse save(TestRequest request) {
    return null;
  }

  @Override
  public TestResponse update(Long id, TestRequest request) {
    return null;
  }

  @Override
  public List<TestResponse> getTestsByLesson(Long id, Authentication authentication) {
    Account account = (Account) authentication.getPrincipal();
    return customTestRepository.getTestsByLesson(id, account.getId());
  }

  @Override
  public TestResponse getById(Long id) {
    return customTestRepository.getTestById(id).orElseThrow(
        () -> new NotFoundException("Test with id " + id + " not found")
    );
  }

  @Override
  public String delete(Long id) {
    Test test = repository.findById(id).orElseThrow(() ->
        new NotFoundException("Test with id " + id + " not found!"));
    repository.delete(test);
    return "Test with id %s deleted successfully";
  }
}
