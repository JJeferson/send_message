package com.send_message.application.service;

import com.send_message.application.config.controller_advice.exceptions.BadGatewayException;
import com.send_message.application.in.SendMessageUseCase;
import com.send_message.application.mapper.MessageRecivedToSendMessage;
import com.send_message.application.validation.MessageRecivedValidate;
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
    @Autowired
    private MessageRecivedValidate validation;
    @Autowired
    SendMessageEmail sendMessageEmail;
    @Autowired
    SendMessagePush sendMessagePush;
    @Autowired
    SendMessageSMS sendMessageSMS;


    @Override
    public String ConvertAndSend(MessageRecived message) {
        validation.validate(message);
        if(message.getNotificationType().equals(NotificationType.Email)) {
            return sendMessageEmail.sendMessage(mapper.convert(message));
        }
        if(message.getNotificationType().equals(NotificationType.Push)) {
            return sendMessagePush.sendMessage(mapper.convert(message));
        }
        if(message.getNotificationType().equals(NotificationType.SMS)) {
            return sendMessageSMS.sendMessage(mapper.convert(message));
        }
        throw new BadGatewayException("Problem with the Notification Type informed");
    }
}
