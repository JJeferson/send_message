package com.send_message.application.mapper;

import com.send_message.application.config.controller_advice.exceptions.NotFoundException;
import com.send_message.domain.MessageRecived;
import com.send_message.domain.SendMessage;
import com.send_message.domain.User;
import com.send_message.framework.out.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class MessageRecivedToSendMessage {

    @Autowired
    UserRepository userRepository;

    public SendMessage convert(MessageRecived messageRecived, User user){
        return SendMessage.builder()
                .message(messageRecived.getMessage())
                .category(messageRecived.getCategory())
                .logDateTime(LocalDateTime.now())
                .user(user)
                .build();
    }

}
