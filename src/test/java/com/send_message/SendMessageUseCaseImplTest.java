package com.send_message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.send_message.application.mapper.MessageRecivedToSendMessage;
import com.send_message.application.service.SendMessageUseCaseImpl;
import com.send_message.application.validation.MessageRecivedValidate;
import com.send_message.domain.MessageRecived;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import com.send_message.framework.out.SendMessageEmail;
import com.send_message.framework.out.SendMessagePush;
import com.send_message.framework.out.SendMessageSMS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SendMessageUseCaseImplTest {

    @InjectMocks
    private SendMessageUseCaseImpl sendMessageUseCase;

    @Mock
    private MessageRecivedToSendMessage mapper;

    @Mock
    private MessageRecivedValidate validation;

    @Mock
    private SendMessageEmail sendMessageEmail;

    @Mock
    private SendMessagePush sendMessagePush;

    @Mock
    private SendMessageSMS sendMessageSMS;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertAndSend_EmailNotification() {
        MessageRecived message = MessageRecived.builder()
                .notificationType(NotificationType.Email)
                .message("Test message")
                .category(Category.Films)
                .build();

        when(sendMessageEmail.sendMessage(any())).thenReturn("Email sent");

        String response = sendMessageUseCase.ConvertAndSend(message);

        assertEquals("Email sent", response);
        verify(validation).validate(message);
        verify(sendMessageEmail).sendMessage(any());
    }

    @Test
    public void testConvertAndSend_PushNotification() {
        MessageRecived message = MessageRecived.builder()
                .notificationType(NotificationType.Push)
                .message("Test message")
                .category(Category.Films)
                .build();

        when(sendMessagePush.sendMessage(any())).thenReturn("Push sent");

        String response = sendMessageUseCase.ConvertAndSend(message);

        assertEquals("Push sent", response);
        verify(validation).validate(message);
        verify(sendMessagePush).sendMessage(any());
    }

    @Test
    public void testConvertAndSend_SMSNotification() {
        MessageRecived message = MessageRecived.builder()
                .notificationType(NotificationType.SMS)
                .message("Test message")
                .category(Category.Films)
                .build();

        when(sendMessageSMS.sendMessage(any())).thenReturn("SMS sent");

        String response = sendMessageUseCase.ConvertAndSend(message);

        assertEquals("SMS sent", response);
        verify(validation).validate(message);
        verify(sendMessageSMS).sendMessage(any());
    }


}
