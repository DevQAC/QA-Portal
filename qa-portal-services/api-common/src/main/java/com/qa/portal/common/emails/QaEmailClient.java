package com.qa.portal.common.emails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class QaEmailClient {

    private JavaMailSender javaMailSender;

    @Autowired
    public QaEmailClient(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    //    method that sends an email
	public void sendEmail(String emailAddress,String emailSubject,String emailBody) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailAddress);

        msg.setSubject(emailSubject);
        msg.setText(emailBody);

        javaMailSender.send(msg);

    }

}