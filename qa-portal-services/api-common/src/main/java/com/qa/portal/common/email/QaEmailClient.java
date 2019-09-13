package com.qa.portal.common.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class QaEmailClient {

    private JavaMailSender javaMailSender;

    public QaEmailClient(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String emailAddress, String emailSubject, String emailBody) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailAddress);
        msg.setSubject(emailSubject);
        msg.setText(emailBody);
        javaMailSender.send(msg);
    }
}