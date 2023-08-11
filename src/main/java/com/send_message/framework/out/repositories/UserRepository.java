package com.send_message.framework.out.repositories;

import com.send_message.domain.User;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface UserRepository extends MongoRepository<User,String> {
    @Query("{'Channels': ?0, 'Subscribed': ?1}")
    List<User> findDistinctByChannelsAndSubscribed(NotificationType channel, Category subscribed);

}

