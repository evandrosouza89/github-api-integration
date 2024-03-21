package com.github.integration.backend.controller;

import com.github.integration.backend.controller.entities.ListRestRequest;
import com.github.integration.backend.controller.entities.ListRestResponse;
import com.github.integration.backend.service.ServiceBusiness;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class ServiceController {

    public static final String LIST_ENDPOINT = "/repositories";

    private final ServiceBusiness business;

    @PostMapping(
            value = LIST_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ListRestResponse list(@Valid @RequestBody final ListRestRequest listRestRequest) {

        log.info(String.format("[Rest] POST /repositories request body: %s", listRestRequest));

        return business.listTop10ByLanguageAndSort(listRestRequest);

    }

}
