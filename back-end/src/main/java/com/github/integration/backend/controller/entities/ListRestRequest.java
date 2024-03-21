package com.github.integration.backend.controller.entities;

import com.github.integration.backend.service.validator.ValidLanguage;
import com.github.integration.backend.service.validator.ValidSortingBy;
import lombok.*;

@ToString
@Getter
@Builder
public class ListRestRequest {

    @ValidLanguage(message = "Please provide a valid language value!")
    private final String language;

    @ValidSortingBy(message = "Please provide a valid sortingBy value!")
    private final String sortingBy;

}