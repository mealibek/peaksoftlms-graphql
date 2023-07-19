package com.peaksoft.lms.services;

import com.peaksoft.lms.dto.requests.presentation.PresentationRequest;
import com.peaksoft.lms.dto.responses.presentation.PresentationResponse;
import java.util.List;

public interface PresentationService {
  PresentationResponse save(PresentationRequest request);
  PresentationResponse update(Long id, PresentationRequest request);
  PresentationResponse getById(Long id);
  List<PresentationResponse> getAll();
  String delete(Long id);
}
