package com.github.integration.backend.controller.exception;

import lombok.NonNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    static final String TIMESTAMP = "timestamp";

    static final String STATUS = "status";

    static final String ERRORS = "errors";
    static final String INPUT_BODY_CANNOT_BE_NULL = "Input body cannot be null";

    // Error handler for @Valid annotation
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final @NonNull HttpHeaders headers,
                                                                  final @NonNull HttpStatusCode status,
                                                                  final @NonNull WebRequest request) {

        //Get all errors
        final List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        final Map<String, Object> errorBody = buildErrorBody(status, errors);

        return new ResponseEntity<>(errorBody, headers, status);

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(final @NonNull Exception ex,
                                                             final Object body,
                                                             final @NonNull HttpHeaders headers,
                                                             final @NonNull HttpStatusCode status,
                                                             final @NonNull WebRequest request) {

        final String error;

        if (ex instanceof HttpMessageNotReadableException) {
            error = INPUT_BODY_CANNOT_BE_NULL;
        } else {
            error = ex.getMessage();
        }

        final Map<String, Object> errorBody = buildErrorBody(status, error);

        return new ResponseEntity<>(errorBody, headers, status);

    }

    private static Map<String, Object> buildErrorBody(final HttpStatusCode status, final Object errors) {

        final Map<String, Object> errorBody = new HashMap<>();

        errorBody.put(TIMESTAMP, new Date());

        errorBody.put(STATUS, status.value());

        errorBody.put(ERRORS, errors);

        return errorBody;

    }
}