package com.github.integration.backend.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EnumLanguages {

    C("c"),
    PHP("php"),
    JAVA("java"),
    PYTHON("python"),
    JAVA_SCRIPT("javascript");

    private final String value;

    public static EnumLanguages getByValue(final String language) {

        for (final EnumLanguages enumLanguage : values()) {
            if (enumLanguage.getValue().equalsIgnoreCase(language)) {
                return enumLanguage;
            }
        }

        return null;

    }

}
