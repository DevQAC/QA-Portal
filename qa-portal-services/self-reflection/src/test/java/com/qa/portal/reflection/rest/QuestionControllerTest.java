package com.qa.portal.reflection.rest;

import com.qa.portal.SelfReflectionApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = SelfReflectionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionControllerTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders httpHeaders = new HttpHeaders();

    @Test
    public void testGetUserRoles() {
        HttpEntity<Map> request = new HttpEntity<>(null, httpHeaders);
        try {
            LOGGER.debug("Calling get Questions for Cohort");
            ResponseEntity<Set> response = restTemplate.getForEntity(createURLWithPort("/self-reflection-api/question/cohort/1"), Set.class);
            LOGGER.debug("Called get questions for cohort - response is " + response.getBody());
            response.getBody().stream().forEach(q -> LOGGER.info(q.toString()));
        }
        catch (Exception e) {
            LOGGER.error("Exception " + e.getMessage());
        }
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}


