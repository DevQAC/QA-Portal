package com.qa.portal.common.emails;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QaEmailClientTest {

    @Mock
    private JavaMailSender javaMailSender;

//    @Mock
//    private SimpleMailMessage msg;

    @InjectMocks
    private QaEmailClient emailClient;

//    private JavaMailSenderImpl javaMailSender2;


    @Test
    public void testEmailWasSent() {

        String emailAddress = "qas@gmail.com";
        String emailSubject = "test";
        String emailBody = "testing";

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        emailClient.sendEmail(emailAddress,emailSubject,emailBody);
        Mockito.verify(this.javaMailSender, Mockito.times(1)).send(captor.capture());
        SimpleMailMessage message = captor.getValue();


        assertEquals(1, Objects.requireNonNull(message.getTo()).length);
        assertEquals(emailAddress, message.getTo()[0]);
        assertEquals(emailSubject, message.getSubject());
        assertEquals(emailBody, message.getText());

    }

}