package com.qa.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {JpaRepositoriesAutoConfiguration.class, DataSourceAutoConfiguration.class})
@EnableMongoRepositories
public class CvApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(CvApiApplication.class, args);
    }
}
