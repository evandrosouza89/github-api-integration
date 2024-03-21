package com.github.integration.backend;

import com.github.integration.backend.common.EnumLanguages;
import com.github.integration.backend.common.EnumSortingBy;
import com.github.integration.backend.controller.ServiceController;
import com.github.integration.backend.controller.entities.ListRestRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class IntegrationTests {

    @Autowired
    private ServiceController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void test_actuatorHealth() {

        // Given

        final String baseUrl = "http://localhost:" + port + "/api/actuator/health";

        final HttpStatusCode expectedStatusCode = HttpStatus.OK;

        final String expectedResponse = "{\"status\":\"UP\"}";

        // When

        final ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);

        // Then

        assertEquals(expectedStatusCode, response.getStatusCode());

        assertEquals(expectedResponse, response.getBody());


    }

    @Test
    void test_list() {

        // Given

        final String url = "http://localhost:" + port + "/api/" + ServiceController.LIST_ENDPOINT;

        final ListRestRequest requestBody = ListRestRequest.builder()
                .language(EnumLanguages.values()[(int) (Math.random() * EnumLanguages.values().length)].getValue())
                .sortingBy(EnumSortingBy.values()[(int) (Math.random() * EnumSortingBy.values().length)].getValue())
                .build();

        final HttpStatusCode expectedStatusCode = HttpStatus.OK;

        // When

        final ResponseEntity<String> actualResponse
                = restTemplate.postForEntity(url, requestBody, String.class);

        // Then

        assertEquals(expectedStatusCode, actualResponse.getStatusCode());

        assertNotNull(actualResponse.getBody());

    }

    @Test
    void test_list_validation_exception() {

        // Given

        final String url = "http://localhost:" + port + "/api/" + ServiceController.LIST_ENDPOINT;

        final ListRestRequest requestBody = ListRestRequest.builder().build();

        final HttpStatusCode expectedStatusCode = HttpStatus.BAD_REQUEST;

        // When

        final ResponseEntity<String> actualResponse
                = restTemplate.postForEntity(url, requestBody, String.class);

        // Then

        assertEquals(expectedStatusCode, actualResponse.getStatusCode());

    }

    @Test
    void test_list_errorHandlingReturnsBadRequest() {

        // Given

        final String url = "http://localhost:" + port + "/wrong";

        final HttpStatus expectedStatusCode = HttpStatus.NOT_FOUND;

        // When

        final ResponseEntity<String> actualResponse
                = restTemplate.postForEntity(url, null, String.class);

        // Then

        assertNotNull(actualResponse);

        assertEquals(expectedStatusCode, actualResponse.getStatusCode());

    }

}