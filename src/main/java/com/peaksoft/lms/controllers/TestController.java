package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.test.TestRequest;
import com.peaksoft.lms.dto.responses.test.TestResponse;
import com.peaksoft.lms.services.TestService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@RequiredArgsConstructor
@Validated
public class TestController {

  private final TestService testService;

  @MutationMapping(name = "saveTest")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public TestResponse saveTest(@Argument TestRequest request) {
    return testService.save(request);
  }

  @MutationMapping(name = "updateTest")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public TestResponse updateTest(@Argument Long id, @Argument TestRequest request) {
    return testService.update(id, request);
  }


  @QueryMapping(name = "getTestsByLesson")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
  public List<TestResponse> getTestsByLesson(@Argument Long lessonId,Authentication authentication) {
    return testService.getTestsByLesson(lessonId, authentication);
  }

  @QueryMapping(name = "getTest")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
  public TestResponse getTest(@Argument Long id) {
    return testService.getById(id);
  }

  @MutationMapping(name = "deleteTest")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public String deleteTest(@Argument Long id) {
    return testService.delete(id);
  }
}
