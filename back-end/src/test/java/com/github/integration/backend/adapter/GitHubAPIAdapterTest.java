package com.github.integration.backend.adapter;

import com.github.integration.backend.common.EnumLanguages;
import com.github.integration.backend.common.EnumSortingBy;
import com.github.integration.backend.common.entities.SearchResultDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GitHubAPIAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GitHubAPIAdapter underTest;


    @Test
    void test_listTop10ByLanguageAndSortByStars() {

        // Given

        final EnumLanguages language = EnumLanguages.C;

        final EnumSortingBy sortingBy = EnumSortingBy.STARS;

        final SearchResultDTO expectedResult = new SearchResultDTO(0, false, null);

        doReturn(expectedResult).when(restTemplate)
                .getForObject(anyString(), eq(SearchResultDTO.class));

        // When

        final SearchResultDTO actualResult = underTest.listTop10ByLanguageAndSortByStars(language, sortingBy);

        // Then

        verify(restTemplate).getForObject(anyString(), eq(SearchResultDTO.class));

        assertEquals(expectedResult, actualResult);

    }

    @Test
    void test_restTemplate() {

        // Given

        final RestTemplateBuilder builder = new RestTemplateBuilder();

        // When

        final RestTemplate actualResult = underTest.restTemplate(builder);

        // Then

        assertNotNull(actualResult);

    }

}
