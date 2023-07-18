package com.peaksoft.lms.exceptions;

import com.peaksoft.lms.exceptions.enums.CustomErrorTypes;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class AlreadyExistException extends RuntimeException implements GraphQLError {

  public AlreadyExistException(String message) {
    super(message);
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null; // Implement if needed
  }

  @Override
  public CustomErrorTypes getErrorType() {
    return CustomErrorTypes.ALREADY_EXIST;
  }
}
