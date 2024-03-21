package com.github.integration.backend.common.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RepositoryDTO(Long id,
                            String name,
                            OwnerDTO owner,
                            @JsonProperty("html_url") String htmlUrl,
                            String description,
                            @JsonProperty("watchers_count") Integer watchersCount) {

}