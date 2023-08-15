package com.send_message;

import com.send_message.application.mapper.SendMessageToSendedLog;
import com.send_message.domain.SendMessage;
import com.send_message.domain.SendedLog;
import com.send_message.domain.User;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import com.send_message.framework.out.SendMessageSMS;
import com.send_message.framework.out.repositories.LogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SendMessageSMSTest {

    @InjectMocks
    private SendMessageSMS sendMessageSMS;

    @Mock
    private SendMessageToSendedLog mapper;

    @Mock
    private LogRepository log;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMessage_SMSNotification() {
        var message = SendMessage.builder()
                .notificationType(NotificationType.SMS)
                .message("Test message")
                .category(Category.Films)
                .user(User.builder().ID("123").name("User1").build())
                .build();

        when(mapper.convert(any(), any(), any(), any())).thenReturn(SendedLog.builder().build());

       sendMessageSMS.sendMessage(message);

        verify(mapper, times(1)).convert(any(), any(), any(), any());
        verify(log, times(1)).save(any());
    }
}
