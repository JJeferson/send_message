package com.send_message;

import com.send_message.application.mapper.SendMessageToSendedLog;
import com.send_message.domain.SendMessage;
import com.send_message.domain.SendedLog;
import com.send_message.domain.User;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import com.send_message.framework.out.SendMessagePush;
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

public class SendMessagePushTest {

    @InjectMocks
    private SendMessagePush sendMessagePush;

    @Mock
    private SendMessageToSendedLog mapper;

    @Mock
    private LogRepository log;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMessage_PushNotification() {
        var message = SendMessage.builder()
                .notificationType(NotificationType.Push)
                .message("Test message")
                .category(Category.Films)
                .user(User.builder().ID("123").name("User1").build())
                .build();

        when(mapper.convert(any(), any(), any(), any())).thenReturn(SendedLog.builder().build());

        sendMessagePush.sendMessage(message);

        verify(mapper, times(1)).convert(any(), any(), any(), any());
        verify(log, times(1)).save(any());
    }
}
