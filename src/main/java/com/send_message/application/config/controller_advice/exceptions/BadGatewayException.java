package com.send_message.application.config.controller_advice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends RuntimeException{
    private static final long serialVersionUID = 5291022396354246343L;

    public BadGatewayException(String message) {
        super(message);
    }
}
