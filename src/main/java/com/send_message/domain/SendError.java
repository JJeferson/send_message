package com.send_message.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SendError {
    LocalDateTime logDateTime;
    String error;
}
