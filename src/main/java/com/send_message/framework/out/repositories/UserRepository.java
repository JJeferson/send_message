package com.send_message.framework.out.repositories;

import com.send_message.domain.User;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface UserRepository extends MongoRepository<User,String> {


    @Query("{'Subscribed': ?0}")
    List<User> findBySubscribedContaining(Category category);

}

