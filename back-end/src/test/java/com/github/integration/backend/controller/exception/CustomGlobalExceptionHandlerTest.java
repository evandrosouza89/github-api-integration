package com.github.integration.backend.controller.exception;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

import static com.github.integration.backend.controller.exception.CustomGlobalExceptionHandler.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomGlobalExceptionHandlerTest {

    private final CustomGlobalExceptionHandler underTest = new CustomGlobalExceptionHandler();


    @Test
    void test_handleMethodArgumentNotValid() {

        // Given

        final MethodParameter methodParameter = mock(MethodParameter.class);

        final BindingResult bindingResult = mock(BindingResult.class);

        final MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);

        final HttpHeaders expectedHeaders = new HttpHeaders();

        final HttpStatusCode expectedStatusCode = HttpStatusCode.valueOf(400);

        final WebRequest request = mock(WebRequest.class);

        // When

        final ResponseEntity<Object> actualResponse = underTest.handleMethodArgumentNotValid(
                ex,
                expectedHeaders,
                expectedStatusCode,
                request
        );

        // Then

        assertNotNull(actualResponse);

        assertEquals(expectedStatusCode, actualResponse.getStatusCode());

        assertEquals(expectedHeaders, actualResponse.getHeaders());

        assertInstanceOf(HashMap.class, actualResponse.getBody());

        @SuppressWarnings("unchecked") final Map<String, Object> actualBody = (Map<String, Object>) actualResponse.getBody();

        assertNotNull(actualBody);

        assertTrue(actualBody.containsKey(TIMESTAMP));

        assertTrue(actualBody.containsKey(STATUS));

        assertTrue(actualBody.containsKey(ERRORS));

    }

    @Test
    void test_handleExceptionInternal() {

        // Given

        final String expectedErrorMsg = "expectedErrorMsg";

        final Exception ex = new Exception(expectedErrorMsg);

        final Object body = new Object();

        final HttpHeaders expectedHeaders = new HttpHeaders();

        final HttpStatusCode expectedStatusCode = HttpStatusCode.valueOf(500);

        final WebRequest request = mock(WebRequest.class);

        // When

        final ResponseEntity<Object> actualResponse = underTest.handleExceptionInternal(
                ex,
                body,
                expectedHeaders,
                expectedStatusCode,
                request
        );

        // Then

        assertNotNull(actualResponse);

        assertEquals(expectedStatusCode, actualResponse.getStatusCode());

        assertEquals(expectedHeaders, actualResponse.getHeaders());

        assertInstanceOf(HashMap.class, actualResponse.getBody());

        @SuppressWarnings("unchecked") final Map<String, Object> actualBody = (Map<String, Object>) actualResponse.getBody();

        assertNotNull(actualBody);

        assertTrue(actualBody.containsKey(TIMESTAMP));

        assertTrue(actualBody.containsKey(STATUS));

        assertTrue(actualBody.containsKey(ERRORS));

        assertEquals(expectedErrorMsg, actualBody.get(ERRORS));

    }

    @Test
    void test_handleExceptionInternal_httpMessageNotReadableException() {

        // Given

        final String errorMsg = "errorMsg";

        final Exception ex = new HttpMessageNotReadableException(errorMsg, new MockClientHttpResponse());

        final Object body = new Object();

        final HttpHeaders expectedHeaders = new HttpHeaders();

        final HttpStatusCode expectedStatusCode = HttpStatusCode.valueOf(500);

        final WebRequest request = mock(WebRequest.class);

        // When

        final ResponseEntity<Object> actualResponse = underTest.handleExceptionInternal(
                ex,
                body,
                expectedHeaders,
                expectedStatusCode,
                request
        );

        // Then

        assertNotNull(actualResponse);

        assertEquals(expectedStatusCode, actualResponse.getStatusCode());

        assertEquals(expectedHeaders, actualResponse.getHeaders());

        assertInstanceOf(HashMap.class, actualResponse.getBody());

        @SuppressWarnings("unchecked") final Map<String, Object> actualBody = (Map<String, Object>) actualResponse.getBody();

        assertNotNull(actualBody);

        assertTrue(actualBody.containsKey(TIMESTAMP));

        assertTrue(actualBody.containsKey(STATUS));

        assertTrue(actualBody.containsKey(ERRORS));

        assertEquals(INPUT_BODY_CANNOT_BE_NULL, actualBody.get(ERRORS));

    }


}
