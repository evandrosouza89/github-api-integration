package com.github.integration.backend.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorMessage {

    INVALID_LANGUAGE("Unable to identify a valid language from the request payload."),

    INVALID_SORTING_PARAM("Unable to identify a sorting parameter from the request payload."),

    ADAPTER_FAILED("Service encountered a failure as it was unable to retrieve a response from the GitHub API.");

    private final String message;

}
