package com.send_message.framework.out.repositories;

import com.send_message.domain.SendedLog;
import com.send_message.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<SendedLog,String> {
}
