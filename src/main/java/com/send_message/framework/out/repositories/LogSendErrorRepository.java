package com.send_message.framework.out.repositories;

import com.send_message.domain.SendError;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogSendErrorRepository extends MongoRepository<SendError,String> {
}

