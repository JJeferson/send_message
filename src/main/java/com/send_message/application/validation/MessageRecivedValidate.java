package com.send_message.application.validation;

import com.send_message.application.config.controller_advice.exceptions.BadGatewayException;
import com.send_message.domain.MessageRecived;
import org.springframework.stereotype.Component;

@Component
public class MessageRecivedValidate   {

    public void validate(MessageRecived messageRecived) {

        if (messageRecived.getMessage() == null || messageRecived.getMessage().isEmpty()) {
            throw new BadGatewayException("Message field cannot be empty");
        }

        if (messageRecived.getCategory() == null) {
            throw new BadGatewayException("Category field cannot be null");
        }

        if (messageRecived.getNotificationType() == null) {
            throw new BadGatewayException("NotificationType field cannot be null");
        }
    }

}
