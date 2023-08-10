package com.send_message.application.in;

import com.send_message.domain.MessageRecived;

public interface SendMessageUseCase {
    String ConvertAndSend(MessageRecived message);
}
