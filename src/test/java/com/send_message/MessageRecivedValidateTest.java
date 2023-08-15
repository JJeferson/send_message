package com.send_message;
import static org.junit.jupiter.api.Assertions.*;

import com.send_message.application.config.controller_advice.exceptions.BadGatewayException;
import com.send_message.application.config.controller_advice.exceptions.NotFoundException;
import com.send_message.application.validation.MessageRecivedValidate;
import com.send_message.domain.MessageRecived;
import com.send_message.domain.enums.Category;
import org.junit.jupiter.api.Test;

public class MessageRecivedValidateTest {

    private final MessageRecivedValidate validator = new MessageRecivedValidate();

    @Test
    public void testValidate_ValidMessageRecived() {
        MessageRecived message = MessageRecived.builder()
                .message("Test message")
                .category(Category.Films)
                .build();

        assertDoesNotThrow(() -> validator.validateMessageRecived(message));
    }

    @Test
    public void testValidate_NullMessage() {
        MessageRecived message = MessageRecived.builder()
                .category(Category.Films)
                .build();

        assertThrows(BadGatewayException.class, () -> validator.validateMessageRecived(message));
    }

    @Test
    public void testValidate_NullCategory() {
        MessageRecived message = MessageRecived.builder()
                .message("Test message")
                .build();

        assertThrows(BadGatewayException.class, () -> validator.validateMessageRecived(message));
    }
    @Test
    public void testValidate_UsersNull() {
        assertThrows(NotFoundException.class, () -> validator.validateUserList(null));
    }

}
