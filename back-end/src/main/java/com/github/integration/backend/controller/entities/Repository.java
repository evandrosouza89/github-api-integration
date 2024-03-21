package com.github.integration.backend.controller.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class Repository {

    private final Long id;

    private final String name;

    private final Owner owner;

    private final String htmlUrl;

    private final String description;

    private final Integer watchersCount;

}