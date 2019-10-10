package com.qa.portal.common.email;

import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class JavaMailSenderFactory {

    public static final String SPRING_MAIL_HOST_PROPERTY = "spring.mail.host";

    public static final String SPRING_MAIL_PORT_PROPERTY = "spring.mail.port";

    public static final String SPRING_MAIL_USERNAME_PROPERTY = "spring.mail.username";

    public static final String SPRING_MAIL_PASSWORD_PROPERTY = "spring.mail.password";

    public static final String SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH_PROPERTY = "spring.mail.properties.mail.smtp.auth";

    public static final String SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE_PROPERTY = "spring.mail.properties.mail.smtp.starttls.enable";

    public static final String MAIL_SMTP_AUTH_PROPERTY = "mail.smtp.auth";

    public static final String MAIL_SMTP_STARTTLS_ENABLE_PROPERTY = "mail.smtp.starttls.enable";

    private Environment environment;

    public JavaMailSenderFactory(Environment environment) {
        this.environment = environment;
    }

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(environment.getProperty(SPRING_MAIL_HOST_PROPERTY));
        javaMailSender.setPort(Integer.parseInt(environment.getProperty(SPRING_MAIL_PORT_PROPERTY)));
        javaMailSender.setUsername(environment.getProperty(SPRING_MAIL_USERNAME_PROPERTY));
        javaMailSender.setPassword(environment.getProperty(SPRING_MAIL_PASSWORD_PROPERTY));
        Properties props = javaMailSender.getJavaMailProperties();
        props.put(MAIL_SMTP_AUTH_PROPERTY, environment.getProperty(SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH_PROPERTY));
        props.put(MAIL_SMTP_STARTTLS_ENABLE_PROPERTY, environment.getProperty(SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE_PROPERTY));
        return javaMailSender;
    }
}
