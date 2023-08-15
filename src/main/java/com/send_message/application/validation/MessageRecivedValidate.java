package com.send_message.application.validation;

import com.send_message.application.config.controller_advice.exceptions.BadGatewayException;
import com.send_message.application.config.controller_advice.exceptions.NotFoundException;
import com.send_message.domain.MessageRecived;
import com.send_message.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageRecivedValidate   {

    public void validateMessageRecived(MessageRecived messageRecived) {

        if (messageRecived.getMessage() == null || messageRecived.getMessage().isEmpty()) {
            throw new BadGatewayException("Message field cannot be empty");
        }

        if (messageRecived.getCategory() == null) {
            throw new BadGatewayException("Category field cannot be null");
        }

    }

    public void validateUserList(List<User> users){
        if(users == null || users.isEmpty()){
            throw new NotFoundException("No client found for sending messages.");
        }
    }

}
