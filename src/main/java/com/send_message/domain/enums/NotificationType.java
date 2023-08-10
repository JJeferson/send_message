package com.send_message.domain.enums;

public enum NotificationType {
    SMS("sms"),
    Email("email"),
    Push("push");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
