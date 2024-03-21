package com.github.integration.backend.service;

import com.github.integration.backend.adapter.GitHubAPIAdapter;
import com.github.integration.backend.common.DTOMapper;
import com.github.integration.backend.common.EnumLanguages;
import com.github.integration.backend.common.EnumSortingBy;
import com.github.integration.backend.common.entities.SearchResultDTO;
import com.github.integration.backend.controller.entities.ListRestRequest;
import com.github.integration.backend.controller.entities.ListRestResponse;
import com.github.integration.backend.exception.BadRequestException;
import com.github.integration.backend.exception.ErrorMessage;
import com.github.integration.backend.exception.InternalServerErrorException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.github.integration.backend.exception.ErrorMessage.*;

@Slf4j
@AllArgsConstructor
@Service
public class ServiceBusiness {

    private final GitHubAPIAdapter adapter;

    public ListRestResponse listTop10ByLanguageAndSort(final ListRestRequest listRestRequest) {

        final EnumLanguages language = extractEnumLanguages(listRestRequest)
                .orElseThrow(() -> logAndBuildBadRequestException(INVALID_LANGUAGE));

        final EnumSortingBy sortingBy = extractSortingBy(listRestRequest)
                .orElseThrow(() -> logAndBuildBadRequestException(INVALID_SORTING_PARAM));

        final SearchResultDTO adapterResponse;

        try {

            adapterResponse = adapter.listTop10ByLanguageAndSortByStars(language, sortingBy);

            log.info("[Business] adapterResponse is: " + adapterResponse);

            return buildRestResponse(adapterResponse)
                    .orElseThrow(Exception::new);

        } catch (final Exception e) {

            throw logAndBuildInternalServerErrorException(ADAPTER_FAILED);

        }

    }

    private static Optional<EnumLanguages> extractEnumLanguages(final ListRestRequest listRestRequest) {
        return Optional.ofNullable(listRestRequest)
                .map(ListRestRequest::getLanguage)
                .map(EnumLanguages::getByValue);
    }

    private static Optional<EnumSortingBy> extractSortingBy(final ListRestRequest listRestRequest) {
        return Optional.ofNullable(listRestRequest)
                .map(ListRestRequest::getSortingBy)
                .map(EnumSortingBy::getByValue);
    }

    private static Optional<ListRestResponse> buildRestResponse(final SearchResultDTO adapterResponse) {
        return Optional.ofNullable(adapterResponse)
                .map(DTOMapper.INSTANCE::searchResultToListRestResponse);
    }

    private InternalServerErrorException logAndBuildInternalServerErrorException(final ErrorMessage errorMessage) {

        log.error("[Business] " + errorMessage.getMessage());

        return new InternalServerErrorException(errorMessage);

    }

    private BadRequestException logAndBuildBadRequestException(final ErrorMessage errorMessage) {

        log.warn("[Business] " + errorMessage.getMessage());

        return new BadRequestException(errorMessage);

    }
}