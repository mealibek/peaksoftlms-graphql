package com.peaksoft.lms.exceptions;

import com.peaksoft.lms.exceptions.enums.CustomErrorTypes;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class NotFoundException extends RuntimeException implements GraphQLError {

  public NotFoundException(String message) {
    super(message);
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public ErrorClassification getErrorType() {
    return CustomErrorTypes.NOT_FOUND;
  }
}
