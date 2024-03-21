package com.github.integration.backend.controller;

import com.github.integration.backend.controller.entities.ListRestRequest;
import com.github.integration.backend.controller.entities.ListRestResponse;
import com.github.integration.backend.service.ServiceBusiness;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class ServiceControllerTest {

    @Mock
    private ServiceBusiness business;

    @InjectMocks
    private ServiceController underTest;

    @Test
    void test_list() {

        // Given

        final ListRestRequest input = ListRestRequest.builder().build();

        final ListRestResponse expectedResponse = ListRestResponse.builder().build();

        doReturn(expectedResponse)
                .when(business).listTop10ByLanguageAndSort(input);

        // When

        final ListRestResponse actualResponse = underTest.list(input);

        // Then

        assertEquals(expectedResponse, actualResponse);

    }

}
