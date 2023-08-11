package com.send_message;
import static org.junit.jupiter.api.Assertions.*;

import com.send_message.application.config.controller_advice.exceptions.BadGatewayException;
import com.send_message.application.validation.MessageRecivedValidate;
import com.send_message.domain.MessageRecived;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import org.junit.jupiter.api.Test;

public class MessageRecivedValidateTest {

    private final MessageRecivedValidate validator = new MessageRecivedValidate();

    @Test
    public void testValidate_ValidMessageRecived() {
        MessageRecived message = MessageRecived.builder()
                .notificationType(NotificationType.Email)
                .message("Test message")
                .category(Category.Films)
                .build();

        assertDoesNotThrow(() -> validator.validate(message));
    }

    @Test
    public void testValidate_NullMessage() {
        MessageRecived message = MessageRecived.builder()
                .notificationType(NotificationType.Email)
                .category(Category.Films)
                .build();

        assertThrows(BadGatewayException.class, () -> validator.validate(message));
    }

    @Test
    public void testValidate_NullCategory() {
        MessageRecived message = MessageRecived.builder()
                .notificationType(NotificationType.Email)
                .message("Test message")
                .build();

        assertThrows(BadGatewayException.class, () -> validator.validate(message));
    }

    @Test
    public void testValidate_NullNotificationType() {
        MessageRecived message = MessageRecived.builder()
                .message("Test message")
                .category(Category.Films)
                .build();

        assertThrows(BadGatewayException.class, () -> validator.validate(message));
    }
}
