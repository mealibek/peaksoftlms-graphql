package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.test.TestResponse;
import java.util.List;
import java.util.Optional;

public interface CustomTestRepository {
  List<TestResponse> getTestsByLesson(Long lessonId,Long userId);
  Optional<TestResponse> getTestById(Long id);

}
