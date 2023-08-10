package com.send_message.framework.out;

import com.send_message.domain.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class SendMessageSMS{
    public String sendMessage(SendMessage message) {
        return "Message "+message.getNotificationType()+" sended";
    }
}
