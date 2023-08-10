package com.send_message.application.mapper;

import com.send_message.domain.MessageRecived;
import com.send_message.domain.SendMessage;
import com.send_message.framework.out.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class MessageRecivedToSendMessage {

    @Autowired
    UserRepository userRepository;

    public SendMessage convert(MessageRecived messageRecived){
        return SendMessage.builder()
                .message(messageRecived.getMessage())
                .category(messageRecived.getCategory())
                .notificationType(messageRecived.getNotificationType())
                .logDateTime(LocalDateTime.now())
                .users(userRepository.findByChannelsContainsAndSubscribedContains(messageRecived.getNotificationType(),messageRecived.getCategory()))
                .build();
    }
}
