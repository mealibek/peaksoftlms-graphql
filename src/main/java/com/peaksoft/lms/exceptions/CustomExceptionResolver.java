package com.peaksoft.lms.exceptions;

import com.peaksoft.lms.exceptions.enums.CustomErrorTypes;
import graphql.GraphqlErrorBuilder;
import jakarta.validation.ValidationException;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {

  @Bean
  public DataFetcherExceptionResolver exceptionResolver() {
    return DataFetcherExceptionResolver.forSingleError((ex, env) -> {
      if (ex instanceof NotFoundException) {
        return GraphqlErrorBuilder.newError(env)
            .errorType(CustomErrorTypes.NOT_FOUND)
            .message(ex.getMessage())
            .path(env.getExecutionStepInfo().getPath())
            .location(env.getField().getSourceLocation())
            .build();
      } else if (ex instanceof AlreadyExistException) {
        return GraphqlErrorBuilder.newError(env)
            .errorType(CustomErrorTypes.ALREADY_EXIST)
            .message(ex.getMessage())
            .path(env.getExecutionStepInfo().getPath())
            .location(env.getField().getSourceLocation())
            .build();
      } else if (ex instanceof BadCredentialsException) {
        return GraphqlErrorBuilder.newError(env)
            .errorType(CustomErrorTypes.BAD_CREDENTIALS)
            .message(ex.getMessage())
            .path(env.getExecutionStepInfo().getPath())
            .location(env.getField().getSourceLocation())
            .build();
      } else if (ex instanceof BadRequestException
          || ex instanceof MethodArgumentNotValidException
          || ex instanceof ValidationException) {
        String message = ex.getMessage();
          if (ex instanceof ValidationException) {
              message = ex.getCause().getMessage();
          }
        return GraphqlErrorBuilder.newError(env)
            .errorType(CustomErrorTypes.BAD_REQUEST)
            .message(message)
            .path(env.getExecutionStepInfo().getPath())
            .location(env.getField().getSourceLocation())
            .build();
      } else {
        return null;
      }
    });
  }
}
