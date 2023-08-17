package com.send_message.application.service;

import com.send_message.application.config.controller_advice.exceptions.BadGatewayException;
import com.send_message.application.config.controller_advice.exceptions.NotFoundException;
import com.send_message.application.in.SendMessageUseCase;
import com.send_message.application.mapper.MessageRecivedToSendMessage;
import com.send_message.application.out.SendMessageStrategy;
import com.send_message.application.validation.MessageRecivedValidate;
import com.send_message.domain.MessageRecived;
import com.send_message.domain.SendError;
import com.send_message.domain.SendMessage;
import com.send_message.domain.User;
import com.send_message.domain.enums.NotificationType;
import com.send_message.framework.out.SendMessageEmail;
import com.send_message.framework.out.SendMessagePush;
import com.send_message.framework.out.SendMessageSMS;
import com.send_message.framework.out.repositories.LogRepository;
import com.send_message.framework.out.repositories.LogSendErrorRepository;
import com.send_message.framework.out.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class SendMessageUseCaseImpl implements SendMessageUseCase {
    @Autowired
    private MessageRecivedToSendMessage mapper;
    @Autowired
    private MessageRecivedValidate validation;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private LogSendErrorRepository logError;

    private final Map<NotificationType, SendMessageStrategy> strategyMap;
    @Autowired
    public SendMessageUseCaseImpl(
            SendMessageEmail sendMessageEmail,
            SendMessagePush sendMessagePush,
            SendMessageSMS sendMessageSMS
    ) {
        strategyMap = Map.of(
                NotificationType.Email, sendMessageEmail,
                NotificationType.Push, sendMessagePush,
                NotificationType.SMS, sendMessageSMS
        );
    }

    @Override
    public String ConvertAndSend(MessageRecived message) {
        validation.validateMessageRecived(message);
        var listedUsers = getDistinctUsers(userRepository.findBySubscribedContaining(message.getCategory()));
        validation.validateUserList(listedUsers);

        Integer messageCount = 0;
        for (User user : listedUsers) {
            var sendMessage = mapper.convert(message,user);
            for (NotificationType nType: user.getChannels()){
                switch (nType) {
                    case SMS:
                        try{
                        sendMessageSelectStrategy(NotificationType.SMS,sendMessage);
                        messageCount = messageCount+1;
                        break;
                        } catch (Exception e) {
                            var error = SendError.builder().logDateTime(LocalDateTime.now()).error("Error for sending message "+nType+" for client"+user.getName()+"").build();
                            logError.save(error);
                        }
                    case Email:
                        try{
                        sendMessageSelectStrategy(NotificationType.Email,sendMessage);
                        messageCount = messageCount+1;
                        break;
                        } catch (Exception e) {
                            var error = SendError.builder().logDateTime(LocalDateTime.now()).error("Error for sending message "+nType+" for client"+user.getName()+"").build();
                            logError.save(error);
                        }
                    case Push:
                        try{
                        sendMessageSelectStrategy(NotificationType.Push,sendMessage);
                        messageCount = messageCount+1;
                        break;
                        } catch (Exception e) {
                             var error = SendError.builder().logDateTime(LocalDateTime.now()).error("Error for sending message "+nType+" for client"+user.getName()+"").build();
                             logError.save(error);
                        }
                    default:
                        throw new BadGatewayException("Problem with the Notification Type.");
                }
            }
        }
        if (messageCount == 0){
            return "0 messages sent.";
        }
        return "were sent "+messageCount+" messages";
    }
    public void sendMessageSelectStrategy(NotificationType notificationType, SendMessage sendMessage){
        sendMessage.setNotificationType(notificationType);
        SendMessageStrategy strategy = strategyMap.get(notificationType);
        strategy.sendMessage(sendMessage);
    }
    public List<User> getDistinctUsers(List<User> users) {
        Set<String> userIds = new HashSet<>();
        List<User> distinctUsers = new ArrayList<>();

        for (User user : users) {
            if (!userIds.contains(user.getID())) {
                userIds.add(user.getID());
                distinctUsers.add(user);
            }
        }

        return distinctUsers;
    }
}
