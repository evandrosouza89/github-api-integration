package com.github.integration.backend.adapter;

import com.github.integration.backend.common.EnumLanguages;
import com.github.integration.backend.common.EnumSortingBy;
import com.github.integration.backend.common.entities.SearchResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class GitHubAPIAdapter {

    private final RestTemplate restTemplate;
    private final String serviceURL;

    public GitHubAPIAdapter(@Lazy final RestTemplate restTemplate,
                            @Value("${adapter.service.url}") final String serviceURL) {
        this.restTemplate = restTemplate;
        this.serviceURL = serviceURL;
    }

    @Bean
    RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.build();
    }

    public SearchResultDTO listTop10ByLanguageAndSortByStars(final EnumLanguages language,
                                                             final EnumSortingBy sortingBy) {

        final String url = String.format(
                "%s/search/repositories?q=language:%s&sort=%s&per_page=10",
                serviceURL,
                language.getValue(),
                sortingBy.getValue());

        log.info("[Adapter] Performing GET " + url);

        return restTemplate.getForObject(url, SearchResultDTO.class);

    }
}
