package com.qa.portal.common.email;

import com.qa.portal.common.exception.QaPortalBusinessException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class QaEmailClient {

    private JavaMailSenderFactory javaMailSenderFactory;

    public QaEmailClient(JavaMailSenderFactory javaMailSenderFactory) {
        this.javaMailSenderFactory = javaMailSenderFactory;
    }

    @Async
    public void sendEmail(String emailAddress, String emailSubject, String emailBody) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(emailAddress);
            msg.setSubject(emailSubject);
            msg.setText(emailBody);
            javaMailSenderFactory.getJavaMailSender().send(msg);
        }
        catch (Exception e ) {
            throw new QaPortalBusinessException("Error sending email");
        }
    }
}