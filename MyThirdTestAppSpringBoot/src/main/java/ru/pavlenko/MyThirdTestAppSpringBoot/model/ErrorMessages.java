package ru.pavlenko.MyThirdTestAppSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {
    EMPTY(""),
    UNKNOWN("Неизвестная ошибка"),
    UNSUPPORTED("Произошла непредвиденная ошибка"),
    VALIDATION("Ошибка валидации");

    private final String description;

    ErrorMessages(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
