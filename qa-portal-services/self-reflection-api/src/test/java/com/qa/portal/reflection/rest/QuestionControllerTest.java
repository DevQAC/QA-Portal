package com.qa.portal.reflection.rest;

import com.qa.portal.SelfReflectionApplication;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = SelfReflectionApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionControllerTest.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	@FlywayTest()
	public void testGetUserRoles() {
		try {
			ResponseEntity<Set> response =
					restTemplate.getForEntity(createURLWithPort("/self-reflection-api/question/cohort/1"), Set.class);
			response.getBody().stream().forEach(q -> LOGGER.info(q.toString()));
			assertThat(6, equalTo(response.getBody().size()));
		} catch (Exception e) {
			Assert.fail("Exception retrieving questions " + e.getMessage());
		}
	}

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
