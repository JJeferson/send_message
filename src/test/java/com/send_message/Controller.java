package com.send_message;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import com.send_message.application.service.SendMessageUseCaseImpl;
import com.send_message.domain.MessageRecived;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import com.send_message.framework.in.MessageController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class Controller {


        @InjectMocks
        private MessageController messageController;
        @Mock
        private SendMessageUseCaseImpl service;

        @Test
        public void testNovaNota() {
           var mock = MessageRecived.builder()
                    .message("123")
                    .category(Category.Films)
                    .build();

            when(service.ConvertAndSend(mock)).thenReturn("Response from service");

            ResponseEntity<String> response = messageController.novaNota(mock);

            // Verifique se o método do serviço foi chamado
            verify(service).ConvertAndSend(mock);

            // Verifique o status de resposta e o corpo da resposta
            assertEquals(200, response.getStatusCodeValue());
            assertEquals("Response from service", response.getBody());
        }


}
