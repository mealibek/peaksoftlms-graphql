package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.test.TestResponse;
import java.util.List;

public interface CustomTestRepository {
  List<TestResponse> getTestsByLesson(Long lessonId,Long userId);
  TestResponse getTestById(Long id);

}
