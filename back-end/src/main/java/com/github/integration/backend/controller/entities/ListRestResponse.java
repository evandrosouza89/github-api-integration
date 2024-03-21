package com.github.integration.backend.controller.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Builder
public class ListRestResponse {

    private final Integer totalCount;

    private final Boolean incompleteResults;

    private final List<Repository> items;

}