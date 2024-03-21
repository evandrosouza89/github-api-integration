package com.github.integration.backend.service.validator;

import com.github.integration.backend.common.EnumLanguages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanguageValidatorTest {

    private final LanguageValidator underTest = new LanguageValidator();

    @ParameterizedTest
    @NullAndEmptySource
    void test_isValid_validInput(final String invalidInput) {

        // Given

        final boolean expectedResult = false;

        // When

        final boolean actualResult = underTest.isValid(invalidInput, null);

        // Then

        assertEquals(expectedResult, actualResult);

    }

    @ParameterizedTest
    @EnumSource(EnumLanguages.class)
    void test_isValid_invalidInput(final EnumLanguages validInput) {

        // Given

        final boolean expectedResult = true;

        // When

        final boolean actualResult = underTest.isValid(validInput.getValue(), null);

        // Then

        assertEquals(expectedResult, actualResult);

    }

}
