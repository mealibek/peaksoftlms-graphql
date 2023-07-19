package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.presentation.PresentationResponse;
import java.util.List;
import java.util.Optional;

public interface CustomPresentationRepository {
  Optional<PresentationResponse> getById(Long id);
  List<PresentationResponse> getAll();
}
