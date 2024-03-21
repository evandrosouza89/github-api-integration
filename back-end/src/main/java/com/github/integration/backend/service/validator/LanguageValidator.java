package com.github.integration.backend.service.validator;

import com.github.integration.backend.common.EnumLanguages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LanguageValidator implements ConstraintValidator<ValidLanguage, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return EnumLanguages.getByValue(value) != null;
    }

}
