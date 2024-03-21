package com.github.integration.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {

    public BadRequestException(final ErrorMessage reason) {
        super(HttpStatus.BAD_REQUEST, reason.getMessage());
    }

}
