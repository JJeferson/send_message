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
public class SendedLog {
    String ID;
    LocalDateTime logDateTime;
    String name;
    String email;
    Integer phone;
    List<Category> Subscribed;
    List<NotificationType> Channels;
}
