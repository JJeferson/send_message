package com.send_message.application.mapper;

import com.send_message.domain.SendMessage;
import com.send_message.domain.SendedLog;
import com.send_message.domain.User;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class SendMessageToSendedLog {

    public SendedLog convert (User user, String message, Category category, NotificationType notificationType){
        return SendedLog.builder()
                .ID(user.getID())
                .logDateTime(LocalDateTime.now())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .Channels(notificationType)
                .Subscribed(category)
                .build();
    }
}
