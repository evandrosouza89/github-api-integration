package com.github.integration.backend.service.validator;

import com.github.integration.backend.common.EnumSortingBy;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortingByValidatorTest {

    private final SortingByValidator underTest = new SortingByValidator();

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
    @EnumSource(EnumSortingBy.class)
    void test_isValid_invalidInput(final EnumSortingBy validInput) {

        // Given

        final boolean expectedResult = true;

        // When

        final boolean actualResult = underTest.isValid(validInput.getValue(), null);

        // Then

        assertEquals(expectedResult, actualResult);

    }

}
