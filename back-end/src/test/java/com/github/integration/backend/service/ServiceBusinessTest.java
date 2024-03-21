package com.github.integration.backend.service;

import com.github.integration.backend.adapter.GitHubAPIAdapter;
import com.github.integration.backend.common.EnumLanguages;
import com.github.integration.backend.common.EnumSortingBy;
import com.github.integration.backend.common.entities.SearchResultDTO;
import com.github.integration.backend.controller.entities.ListRestRequest;
import com.github.integration.backend.controller.entities.ListRestResponse;
import com.github.integration.backend.controller.entities.Repository;
import com.github.integration.backend.exception.BadRequestException;
import com.github.integration.backend.exception.ErrorMessage;
import com.github.integration.backend.exception.InternalServerErrorException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceBusinessTest {

    @Mock
    private GitHubAPIAdapter adapter;

    @InjectMocks
    private ServiceBusiness underTest;

    @Test
    void test_listTop10ByLanguageAndSort() {

        // Given

        final EnumLanguages language = EnumLanguages.C;

        final EnumSortingBy sortingBy = EnumSortingBy.STARS;

        final ListRestRequest input = ListRestRequest.builder()
                .language(language.getValue())
                .sortingBy(sortingBy.getValue())
                .build();

        int expectedTotalCount = 0;

        final boolean expectedIncompleteResults = false;

        final List<Repository> expectedItems = null;

        final SearchResultDTO adapterResponse = new SearchResultDTO(expectedTotalCount, expectedIncompleteResults, null);

        doReturn(adapterResponse)
                .when(adapter).listTop10ByLanguageAndSortByStars(language, sortingBy);

        final ListRestResponse expectedResponse = ListRestResponse.builder()
                .totalCount(expectedTotalCount)
                .incompleteResults(expectedIncompleteResults)
                .items(expectedItems)
                .build();

        // When

        final ListRestResponse actualResponse = underTest.listTop10ByLanguageAndSort(input);

        // Then

        assertThat(actualResponse, samePropertyValuesAs(expectedResponse));

    }

    @ParameterizedTest
    @ValueSource(strings = {"not_a_valid_language"})
    @NullAndEmptySource
    void test_listTop10ByLanguageAndSort_invalid_language(final String invalidLanguage) {

        // Given

        final EnumSortingBy sortingBy = EnumSortingBy.STARS;

        final ListRestRequest input = ListRestRequest.builder()
                .language(invalidLanguage)
                .sortingBy(sortingBy.getValue())
                .build();

        final Class<BadRequestException> expectedExceptionClass = BadRequestException.class;

        final ErrorMessage expectedErrorMsg = ErrorMessage.INVALID_LANGUAGE;

        Exception actualException = null;

        // When

        try {
            underTest.listTop10ByLanguageAndSort(input);
        } catch (final Exception e) {
            actualException = e;
        }

        // Then

        assertNotNull(actualException);

        assertEquals(expectedExceptionClass, actualException.getClass());

        assertEquals(expectedErrorMsg.getMessage(), ((BadRequestException) actualException).getReason());

        verify(adapter, times(0)).listTop10ByLanguageAndSortByStars(any(), any());

    }

    @ParameterizedTest
    @ValueSource(strings = {"not_a_valid_sorting_by"})
    @NullAndEmptySource
    void test_listTop10ByLanguageAndSort_invalid_sortingBy(final String invalidSortingBy) {

        // Given

        final EnumLanguages language = EnumLanguages.C;

        final ListRestRequest input = ListRestRequest.builder()
                .language(language.getValue())
                .sortingBy(invalidSortingBy)
                .build();

        final Class<BadRequestException> expectedExceptionClass = BadRequestException.class;

        final ErrorMessage expectedErrorMsg = ErrorMessage.INVALID_SORTING_PARAM;

        Exception actualException = null;

        // When

        try {
            underTest.listTop10ByLanguageAndSort(input);
        } catch (final Exception e) {
            actualException = e;
        }

        // Then

        assertNotNull(actualException);

        assertEquals(expectedExceptionClass, actualException.getClass());

        assertEquals(expectedErrorMsg.getMessage(), ((BadRequestException) actualException).getReason());

        verify(adapter, times(0)).listTop10ByLanguageAndSortByStars(any(), any());

    }

    @Test
    void test_listTop10ByLanguageAndSort_adapter_returned_null() {

        // Given

        final EnumLanguages language = EnumLanguages.C;

        final EnumSortingBy sortingBy = EnumSortingBy.STARS;

        final ListRestRequest input = ListRestRequest.builder()
                .language(language.getValue())
                .sortingBy(sortingBy.getValue())
                .build();


        final SearchResultDTO adapterResponse = null;

        doReturn(adapterResponse)
                .when(adapter).listTop10ByLanguageAndSortByStars(language, sortingBy);

        final Class<InternalServerErrorException> expectedExceptionClass = InternalServerErrorException.class;

        final ErrorMessage expectedErrorMsg = ErrorMessage.ADAPTER_FAILED;

        Exception actualException = null;

        // When

        try {
            underTest.listTop10ByLanguageAndSort(input);
        } catch (final Exception e) {
            actualException = e;
        }

        // Then

        assertNotNull(actualException);

        assertEquals(expectedExceptionClass, actualException.getClass());

        assertEquals(expectedErrorMsg.getMessage(), ((InternalServerErrorException) actualException).getReason());

        verify(adapter).listTop10ByLanguageAndSortByStars(any(), any());

    }

    @Test
    void test_listTop10ByLanguageAndSort_adapter_threw_exception() {

        // Given

        final EnumLanguages language = EnumLanguages.C;

        final EnumSortingBy sortingBy = EnumSortingBy.STARS;

        final ListRestRequest input = ListRestRequest.builder()
                .language(language.getValue())
                .sortingBy(sortingBy.getValue())
                .build();

        doThrow(new RuntimeException("exception"))
                .when(adapter).listTop10ByLanguageAndSortByStars(language, sortingBy);

        final Class<InternalServerErrorException> expectedExceptionClass = InternalServerErrorException.class;

        final ErrorMessage expectedErrorMsg = ErrorMessage.ADAPTER_FAILED;

        Exception actualException = null;

        // When

        try {
            underTest.listTop10ByLanguageAndSort(input);
        } catch (final Exception e) {
            actualException = e;
        }

        // Then

        assertNotNull(actualException);

        assertEquals(expectedExceptionClass, actualException.getClass());

        assertEquals(expectedErrorMsg.getMessage(), ((InternalServerErrorException) actualException).getReason());

        verify(adapter).listTop10ByLanguageAndSortByStars(any(), any());

    }
}
