package com.send_message.domain;

import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageRecived {
    String message;
    Category category;
}
