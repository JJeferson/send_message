package com.send_message.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum NotificationType {
    SMS("sms"),
    Email("email"),
    Push("push");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }
    @JsonCreator
    public static NotificationType fromValue(String value) {
        for (NotificationType notificationType : NotificationType.values()) {
            if (notificationType.value.equalsIgnoreCase(value)) {
                return notificationType;
            }
        }
        throw new IllegalArgumentException("Invalid NotificationType value: " + value);
    }
    @JsonValue
    public String getValue() {
        return value;
    }
}
