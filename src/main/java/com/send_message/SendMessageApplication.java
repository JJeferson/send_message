package com.send_message;

import com.send_message.domain.User;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@SpringBootApplication
public class SendMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendMessageApplication.class, args);
	}
	@Component
	class DatabaseInitializer implements CommandLineRunner {

		@Autowired
		private MongoTemplate mongoTemplate;



		@Override
		public void run(String... args) throws Exception {
			if (!mongoTemplate.collectionExists("users")) {
				insertMockUsers();
			}
		}

		private void insertMockUsers() {
			List<User> mockUsers = createMockUsers();
			mongoTemplate.insertAll(mockUsers);
		}

		private List<User> createMockUsers() {
			List<User> mockUsers = List.of(
					User.builder().ID("123").name("Teddy")
							.email("teddy@bla.com")
							.phone(123654)
							.Subscribed(List.of(Category.Finance, Category.Films))
							.Channels(List.of(NotificationType.Email,NotificationType.SMS)).build(),
					User.builder().ID("321").name("Tonny")
							.email("tonny@bla.com")
							.phone(123654)
							.Subscribed(List.of(Category.Finance, Category.Sports))
							.Channels(List.of(NotificationType.Email,NotificationType.Push)).build(),
					User.builder().ID("456").name("Tantan")
							.email("tantan@bla.com")
							.phone(456654)
							.Subscribed(List.of(Category.Finance))
							.Channels(List.of(NotificationType.Email)).build(),
					User.builder().ID("654").name("Bonbon")
							.email("bonbon@bla.com")
							.phone(321654)
							.Subscribed(List.of(Category.Films))
							.Channels(List.of(NotificationType.SMS)).build()
			);
			return mockUsers;
		}
	}
}
