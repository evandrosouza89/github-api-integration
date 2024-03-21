package com.github.integration.backend.common.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OwnerDTO(@JsonProperty("avatar_url") String avatarUrl) {

}