package com.send_message.domain;

import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class SendMessage {
    Category category;
    NotificationType notificationType;
    LocalDateTime logDateTime;
    User user;
    String message;
}
