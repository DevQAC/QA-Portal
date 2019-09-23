package com.qa.portal.user.keycloak;

import com.qa.portal.common.email.JavaMailSenderFactory;
import com.qa.portal.common.email.QaEmailClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

//@RunWith(MockitoJUnitRunner.class)
public class EmailTest {

    @Mock
    private JavaMailSenderFactory javaMailSenderFactory;

    @Mock
    private Environment environment;

    private QaEmailClient emailClient;

    private JavaMailSender javaMailSender;


    @Before
    public void setup() {
        javaMailSender = javaMailSender();
        Mockito.when(javaMailSenderFactory.getJavaMailSender()).thenReturn(javaMailSender);
    }

    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("qacportal2019@gmail.com");
        javaMailSender.setPassword("q@m@1l$1");
        Properties props = javaMailSender.getJavaMailProperties();
        props.put( "mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return javaMailSender;
    }

//    @Test
    public void testEmail() {
        JavaMailSenderFactory javaMailSenderFactory = new JavaMailSenderFactory(environment);
        emailClient = new QaEmailClient(javaMailSenderFactory);
        emailClient.sendEmail("scotthmsn@hotmail.com", "Scott Test", "Hello");
    }
}
