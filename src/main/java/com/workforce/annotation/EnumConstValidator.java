package com.workforce.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumConstValidator implements ConstraintValidator<ValidateEnumConstant, String> {

    private Class<? extends Enum<?>> enumClass;
    private String message;
    private boolean ignoreCase;

    @Override
    public void initialize(ValidateEnumConstant constraintAnnotation) {
        this.enumClass = constraintAnnotation.value();
        this.message = constraintAnnotation.message();
        this.ignoreCase = constraintAnnotation.ignoreCase();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        boolean isValid = Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> ignoreCase
                        ? e.name().equalsIgnoreCase(value)
                        : e.name().equals(value));

        if (!isValid && message.isBlank()) {
            String msg = "Invalid value: '" + value + "'. Allowed values: " + Arrays.toString(enumClass.getEnumConstants());

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(msg)
                    .addConstraintViolation();
        }

        return isValid;
    }
}