package com.send_message.domain;

import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class User {
    String ID;
    String name;
    String email;
    Integer phone;
    List<Category> Subscribed;
    List<NotificationType> Channels;
}
