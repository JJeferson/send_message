package com.send_message.framework.in;

import com.send_message.application.service.SendMessageUseCaseImpl;
import com.send_message.domain.MessageRecived;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/send_message")
public class MessageController {
    @Autowired
    SendMessageUseCaseImpl service;

    @CacheEvict(value = "/nova_nota", allEntries = true)
    @PostMapping("/nova_nota")
    public ResponseEntity<String> novaNota(@RequestBody MessageRecived messageRecived)
    {
        var response = service.ConvertAndSend(messageRecived);
        return ResponseEntity.ok(response);
    }
}
