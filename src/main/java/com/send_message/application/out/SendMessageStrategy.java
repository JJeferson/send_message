package com.send_message.application.out;

import com.send_message.domain.SendMessage;

public interface SendMessageStrategy {
    public void  sendMessage(SendMessage message);
}
