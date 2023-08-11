package com.send_message.framework.out;

import com.send_message.application.mapper.SendMessageToSendedLog;
import com.send_message.domain.SendMessage;
import com.send_message.domain.User;
import com.send_message.framework.out.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMessageSMS{
    @Autowired
    private SendMessageToSendedLog mapper;
    @Autowired
    private LogRepository log;
    public String sendMessage(SendMessage message) {
        for (User user : message.getUsers()) {
            log.save(mapper.convert(user,message.getMessage(),message.getCategory(),message.getNotificationType()));
            // sendSMSNotification(message);
        }
        return "Message "+message.getNotificationType()+" sended for "+message.getUsers().size()+" users.";
    }
}
