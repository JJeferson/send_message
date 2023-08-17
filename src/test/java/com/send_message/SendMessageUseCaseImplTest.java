import com.send_message.application.config.controller_advice.exceptions.BadGatewayException;
import com.send_message.application.config.controller_advice.exceptions.NotFoundException;
import com.send_message.application.mapper.MessageRecivedToSendMessage;
import com.send_message.application.out.SendMessageStrategy;
import com.send_message.application.service.SendMessageUseCaseImpl;
import com.send_message.application.validation.MessageRecivedValidate;
import com.send_message.domain.MessageRecived;
import com.send_message.domain.SendMessage;
import com.send_message.domain.User;
import com.send_message.domain.enums.Category;
import com.send_message.domain.enums.NotificationType;
import com.send_message.framework.out.SendMessageEmail;
import com.send_message.framework.out.SendMessagePush;
import com.send_message.framework.out.SendMessageSMS;
import com.send_message.framework.out.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SendMessageUseCaseImplTest {

    @InjectMocks
    private SendMessageUseCaseImpl sendMessageUseCase;

    @Mock
    private MessageRecivedToSendMessage mapper;

    @Mock
    private MessageRecivedValidate validation;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SendMessageEmail sendMessageEmail;

    @Mock
    private SendMessagePush sendMessagePush;

    @Mock
    private SendMessageSMS sendMessageSMS;

    @Mock
    private SendMessageStrategy sendMessageStrategy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertAndSend() {
        MessageRecived messageRecived = MessageRecived.builder().message("teste").category(Category.Films).build();
        messageRecived.setCategory(Category.Finance);

        User user = User.builder().ID("123").
                name("123").
                Channels(List.of(NotificationType.Email,NotificationType.SMS,NotificationType.Push)).
                Subscribed(List.of(Category.Films,Category.Sports,Category.Finance))
                .build();
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findBySubscribedContaining(Category.Finance)).thenReturn(users);
        when(mapper.convert(messageRecived,user)).thenReturn(SendMessage.builder().build());
        var r  = sendMessageUseCase.ConvertAndSend(messageRecived);

        assertEquals("were sent 3 messages",r);
    }

}
