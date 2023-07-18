package com.peaksoft.lms.validation;

import com.peaksoft.lms.exceptions.BadRequestException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValid, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    boolean hasValidity = true;
    if (s == null || s.isBlank()) {
      throw new BadRequestException("Email cannot be empty!");
    }
    String emailValid = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    if (!s.matches(emailValid)) {
      throw new BadRequestException("Email must be a well-formed email address!");
    }
    return hasValidity;
  }
}

