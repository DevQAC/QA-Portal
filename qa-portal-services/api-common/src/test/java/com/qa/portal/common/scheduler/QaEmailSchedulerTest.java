package com.qa.portal.common.scheduler;

import com.qa.portal.common.emails.QaEmailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.sql.SQLOutput;
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class QaEmailSchedulerTest {


    @Mock
    private QaEmailClient emailClient;

    @InjectMocks
    private QaEmailScheduler qae;

//    Test to make sure that the method call for the scheduler works
    @Test
    public void sendScheduledEmails() {
        String emailAddress = "qas@gmail.com";
        String emailSubject = "test";
        String emailBody = "testing";

        System.out.println(qae);
        qae.sendScheduledEmails(emailAddress,emailSubject,emailBody);
        Mockito.verify(this.emailClient, Mockito.times(1)).sendEmail(emailAddress,emailSubject,emailBody);

    }
}