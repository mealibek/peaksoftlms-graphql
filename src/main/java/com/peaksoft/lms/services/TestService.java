package com.peaksoft.lms.services;

import com.peaksoft.lms.dto.requests.test.TestRequest;
import com.peaksoft.lms.dto.responses.test.TestResponse;
import java.util.List;
import org.springframework.security.core.Authentication;

public interface TestService {

  TestResponse save(TestRequest request);
  TestResponse update(Long id,TestRequest request);
  List<TestResponse> getTestsByLesson(Long id,Authentication authentication);
  TestResponse getById(Long id);
  String delete(Long id);
}
