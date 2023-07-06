package com.peaksoft.lms.exceptions;

import com.peaksoft.lms.exceptions.enums.CustomErrorTypes;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class BadRequestException extends RuntimeException implements GraphQLError {
    public BadRequestException(String message) {

        super(message);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return CustomErrorTypes.BAD_REQUEST;
    }
}
