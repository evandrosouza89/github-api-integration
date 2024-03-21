package com.github.integration.backend;

import com.github.integration.backend.adapter.GitHubAPIAdapter;
import com.github.integration.backend.controller.ServiceController;
import com.github.integration.backend.service.ServiceBusiness;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SmokeTests {

    // Given

    @Autowired
    private ServiceController controller;

    @Autowired
    private ServiceBusiness serviceBusiness;

    @Autowired
    private GitHubAPIAdapter gitHubAPIAdapter;

    // When

    @Test
    void contextLoads() {

        // Then

        assertNotNull(controller);

        assertNotNull(serviceBusiness);

        assertNotNull(gitHubAPIAdapter);

    }

}
