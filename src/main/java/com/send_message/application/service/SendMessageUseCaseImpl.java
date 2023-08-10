package com.send_message.application.service;

import com.send_message.application.in.SendMessageUseCase;
import com.send_message.application.mapper.MessageRecivedToSendMessage;
import com.send_message.domain.MessageRecived;
import com.send_message.domain.enums.NotificationType;
import com.send_message.framework.out.SendMessageEmail;
import com.send_message.framework.out.SendMessagePush;
import com.send_message.framework.out.SendMessageSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageUseCaseImpl implements SendMessageUseCase {
    @Autowired
    private MessageRecivedToSendMessage mapper;

    @Override
    public String ConvertAndSend(MessageRecived message) {

        if(message.getNotificationType().equals(NotificationType.Email)) {
            SendMessageEmail sendMessageEmail = new SendMessageEmail();
            return sendMessageEmail.sendMessage(mapper.convert(message));
        }
        if(message.getNotificationType().equals(NotificationType.Push)) {
            SendMessagePush sendMessagePush = new SendMessagePush();
            return sendMessagePush.sendMessage(mapper.convert(message));
        }
        if(message.getNotificationType().equals(NotificationType.SMS)) {
            SendMessageSMS sendMessageSMS = new SendMessageSMS();
            return sendMessageSMS.sendMessage(mapper.convert(message));
        }
        return "ERROR: Notification type informed is incorrect";
    }
}
