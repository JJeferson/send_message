package com.send_message.framework.out;

import com.send_message.application.mapper.SendMessageToSendedLog;
import com.send_message.application.out.SendMessageStrategy;
import com.send_message.domain.SendMessage;
import com.send_message.domain.User;
import com.send_message.framework.out.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessagePush implements SendMessageStrategy {
    @Autowired
    private SendMessageToSendedLog mapper;
    @Autowired
    private LogRepository log;

    @Override
    public void sendMessage(SendMessage message) {
        log.save(mapper.convert(message.getUser(),message.getMessage(),message.getCategory(),message.getNotificationType()));
        // sendSMSNotification(message);
    }
}
