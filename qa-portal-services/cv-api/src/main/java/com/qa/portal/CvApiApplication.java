package com.qa.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
@EnableMongoRepositories
public class CvApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CvApiApplication.class, args);
    }
}
