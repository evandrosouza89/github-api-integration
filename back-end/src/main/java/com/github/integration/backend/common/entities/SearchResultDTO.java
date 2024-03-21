package com.github.integration.backend.common.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SearchResultDTO(@JsonProperty("total_count") Integer totalCount,
                              @JsonProperty("incomplete_results") Boolean incompleteResults,
                              List<RepositoryDTO> items) {

}