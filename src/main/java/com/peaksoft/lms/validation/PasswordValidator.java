package com.peaksoft.lms.validation;

import com.peaksoft.lms.exceptions.BadRequestException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    boolean hasValidity = true;
    if (s == null || s.isBlank()) {
      throw new BadRequestException("Password cannot be empty!");
    }
    if (s.length() > 20 || s.length() < 6) {
      throw new BadRequestException("The password must contain from 6 to 20 characters!");
    }
    String upperCaseChars = "(.*[A-Z].*)";
    if (!s.matches(upperCaseChars)) {
      throw new BadRequestException("The password must contain at least one capital letter!");
    }
    String lowerCaseChars = "(.*[a-z].*)";
    if (!s.matches(lowerCaseChars)) {
      throw new BadRequestException("The password must contain at least one lowercase letter!");
    }
    String numbers = "(.*[0-9].*)";
    if (!s.matches(numbers)) {
      throw new BadRequestException("The password must contain at least one number 0-9!");
    }
    return hasValidity;
  }
}

