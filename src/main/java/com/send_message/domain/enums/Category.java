package com.send_message.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Category {

    Sports("sports"),
    Finance("finance"),
    Films("films");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Category fromValue(String value) {
        for (Category category : Category.values()) {
            if (category.value.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid category value: " + value);
    }

}
