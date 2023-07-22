package com.peaksoft.lms.controllers;

import com.peaksoft.lms.dto.requests.presentation.PresentationRequest;
import com.peaksoft.lms.dto.responses.presentation.PresentationResponse;
import com.peaksoft.lms.services.PresentationService;
import jakarta.validation.Valid;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

@Controller
@RequiredArgsConstructor
@Validated
public class PresentationController {

  private final PresentationService presentationService;

  @MutationMapping(name = "savePresentation")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public PresentationResponse savePresentation(@Argument @Valid PresentationRequest request) {
    return presentationService.save(request);
  }

  @MutationMapping(name = "updatePresentation")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public PresentationResponse updatePresentation(@Argument Long id,
      @Argument @Valid PresentationRequest request) {
    return presentationService.update(id, request);
  }

  @MutationMapping(name = "deletePresentation")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR')")
  public String deletePresentation(@Argument Long id) {
    return presentationService.delete(id);
  }

  @QueryMapping(name = "getPresentations")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
  public List<PresentationResponse> getPresentations() {
    return presentationService.getAll();
  }

  @QueryMapping(name = "getPresentation")
  @PreAuthorize("hasAnyAuthority('ADMIN','INSTRUCTOR','STUDENT')")
  public PresentationResponse getPresentation(@Argument Long id) {
    return presentationService.getById(id);
  }
}
