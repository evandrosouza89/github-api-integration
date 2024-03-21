package com.github.integration.backend.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EnumSortingBy {

    STARS("stars"),
    INTERACTIONS("interactions"),
    REACTIONS("reactions");

    private final String value;

    public static EnumSortingBy getByValue(final String language) {

        for (final EnumSortingBy enumLanguage : values()) {
            if (enumLanguage.getValue().equalsIgnoreCase(language)) {
                return enumLanguage;
            }
        }

        return null;
    }
}
