package es.iespuertodelacruz.xptrade.shared.utils;

import es.iespuertodelacruz.xptrade.dto.RoleDTO;
import es.iespuertodelacruz.xptrade.shared.config.MailService;
import es.iespuertodelacruz.xptrade.utilities.TestUtilities;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MailServiceTest extends TestUtilities {

    @Mock
    JavaMailSender sender;

    @InjectMocks
    MailService service;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        service = new MailService();
        service.setSender(sender);
    }

    @Test
    public void sendTest(){
        String[] to = {"test1@example.com", "test2@example.com"};
        String subject = "Test Subject";
        String content = "This is a test message";

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        service.send(to, subject, content);

        verify(sender, times(1)).send(captor.capture());
        SimpleMailMessage sentMessage = captor.getValue();

        Assertions.assertArrayEquals(to, sentMessage.getTo());
        Assertions.assertEquals(subject, sentMessage.getSubject());
        Assertions.assertEquals(content, sentMessage.getText());
    }
}
