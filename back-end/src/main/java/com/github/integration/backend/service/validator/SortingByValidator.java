package com.github.integration.backend.service.validator;

import com.github.integration.backend.common.EnumSortingBy;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SortingByValidator implements ConstraintValidator<ValidSortingBy, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return EnumSortingBy.getByValue(value) != null;
    }

}
