package com.qa.portal.common.config;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Arrays;
import java.util.Properties;

import static com.qa.portal.common.CommonConstants.DOZER_CONFIG_FILE;

@Configuration
public class CommonConfig {

    public static final String SPRING_MAIL_HOST_PROPERTY = "spring.mail.host";

    public static final String SPRING_MAIL_PORT_PROPERTY = "spring.mail.port";

    public static final String SPRING_MAIL_USERNAME_PROPERTY = "spring.mail.username";

    public static final String SPRING_MAIL_PASSWORD_PROPERTY = "spring.mail.password";

    public static final String SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH_PROPERTY = "spring.mail.properties.mail.smtp.auth";

    public static final String SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE_PROPERTY = "spring.mail.properties.mail.smtp.starttls.enable";

    public static final String MAIL_SMTP_AUTH_PROPERTY = "mail.smtp.auth";

    public static final String MAIL_SMTP_STARTTLS_ENABLE_PROPERTY = "mail.smtp.starttls.enable";

    @Autowired
    private Environment environment;

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList(DOZER_CONFIG_FILE));
        return mapper;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl  javaMailSender = new JavaMailSenderImpl();
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
