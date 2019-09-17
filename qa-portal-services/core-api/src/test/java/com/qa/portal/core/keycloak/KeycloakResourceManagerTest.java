package com.qa.portal.core.keycloak;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.portal.core.dto.QaUserDetailsDto;
import com.qa.portal.common.dto.QaUserDto;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;

//@RunWith(SpringRunner.class)
public class KeycloakResourceManagerTest {

    private final Logger LOGGER = LoggerFactory.getLogger(KeycloakResourceManagerTest.class);

    private RestTemplate restTemplate;

//    @Test
    public void keycloakUserManagerTest() {
        try {
            restTemplate = new RestTemplate();
            LOGGER.info("Sending request");
            ResponseEntity<QaUserDetailsDto> response =
                    restTemplate.postForEntity("http://localhost:8088/admin-api/user", getRequest(), QaUserDetailsDto.class);
            LOGGER.info(response.getBody().toString());
        } catch (Exception e) {
            Assert.fail("Exception updating keycloak " + e.getMessage());
        }
    }

    private HttpEntity<QaUserDetailsDto> getRequest() {
        ObjectMapper om = new ObjectMapper();
        QaUserDetailsDto qaUserDetailsDto = new QaUserDetailsDto();
        QaUserDto userDto = new QaUserDto();
        userDto.setFirstName("Scott");
        userDto.setLastName("Thomson");
        userDto.setUserName("s2@qa.com");
        userDto.setEmail("scotthmsn@hotmail.com");
        qaUserDetailsDto.setUser(userDto);
        qaUserDetailsDto.setRoleName("training-user");
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, qaUserDetailsDto);
            LOGGER.info(sw.toString());
        }
        catch (Exception e) {

        }
        HttpEntity<QaUserDetailsDto> entity = new HttpEntity<>(qaUserDetailsDto, getHeaders());
        return entity;
    }

    private HttpHeaders getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJtOVFpNFExcHU3V1NzbkNQYWh5QUk5SlgxaEJfNGN4S2ZuRmloZG1zb2o0In0.eyJqdGkiOiIzYjM2MWFhMS0wOTM1LTRiNGItYmE4Zi0zY2I3Y2FkMzUxMmEiLCJleHAiOjE1Njg2NTA5NjAsIm5iZiI6MCwiaWF0IjoxNTY4NjUwNjYwLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvcWEtcG9ydGFsIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjkyYjkyODM2LTQ0ZmItNDVjMi04YmVkLTBhNGVmYjY4YjYwMSIsInR5cCI6IkJlYXJlciIsImF6cCI6InFhLXBvcnRhbC11aSIsIm5vbmNlIjoiYTI1MDc3NGUtZWIzZi00YWNhLTgyZjktN2NjZDZkYjI2Y2Q1IiwiYXV0aF90aW1lIjoxNTY4NjUwNjU4LCJzZXNzaW9uX3N0YXRlIjoiN2VjZjQ0ZmMtNjlmNy00YTE3LThhOWItZjQzZTc3Y2Y5NDIxIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJ0cmFpbmluZy11c2VyIiwiY29ob3J0X0NJX0ludGFrZV8xIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiSmltIFRyYWluZWUxIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidHJhaW5lZTFAcWEuY29tIiwiZ2l2ZW5fbmFtZSI6IkppbSIsImZhbWlseV9uYW1lIjoiVHJhaW5lZTEifQ.c528kjLfPaLoeT7VB2vk7PJB0OhSVj-0X5EjI1eqK2P29ZHWMBNDvQbJ4y4nYqRMTJVG8huCNUG4oxCSQNtALD4VHqZIf4Nl0lbFyfhfd-drDZ-78vTjT9BHViwoviVK_CgMm15Ys5muPmSjrdl2zcI3MFnJnZ7NzCUzYAa3XvlobskESLZvsyX0tcgJtKgs9yrpy0XgMFRUOKinylvyiFq7x1-Atx6O8GADI26kvcTWeJxH53zOw8UlXzHYhgTQWjJzq7-doWIU8Vht73gb_0k8_y6VCQzghoZv4m_3rGWB67puUikOXE68h98VB0LNmrSGxiBMMfvlJ6vBM65EVA");
        return headers;
    }
}
