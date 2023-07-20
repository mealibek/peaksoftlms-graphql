package com.peaksoft.lms.validation;

import com.peaksoft.lms.exceptions.BadRequestException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            throw new BadRequestException("Phone number cannot be empty!");
        }
        if (!value.startsWith("+996")) {
            throw new BadRequestException("Phone number must start with '+996'!");
        }
        if (value.length() == 14) {
            throw new BadRequestException("Phone number must have 14 characters including '+996'!");
        }
        return true;
    }
}
