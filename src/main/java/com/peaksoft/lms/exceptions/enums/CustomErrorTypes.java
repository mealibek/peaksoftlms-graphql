package com.peaksoft.lms.exceptions.enums;

import graphql.ErrorClassification;

import javax.lang.model.type.ErrorType;

public enum CustomErrorTypes implements ErrorClassification {
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    INTERNAL_ERROR,
    ALREADY_EXIST,
    BAD_CREDENTIALS,
    INVALID_TOKEN,
    TOKEN_EXPIRED;
}
