package com.qa.portal.admin.keycloak;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.portal.AdminApiApplication;
import com.qa.portal.admin.dto.QaUserAndRoleDto;
import com.qa.portal.common.dto.QaUserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;

@RunWith(SpringRunner.class)
public class KeycloakUserManagerTest {

    private final Logger LOGGER = LoggerFactory.getLogger(KeycloakUserManagerTest.class);

    private RestTemplate restTemplate;

    @Test
    public void keycloakUserManagerTest() {
        try {
            restTemplate = new RestTemplate();
            LOGGER.info("Sending request");
            ResponseEntity<QaUserAndRoleDto> response =
                    restTemplate.postForEntity("http://localhost:8088/admin-api/user", getRequest(), QaUserAndRoleDto.class, getHeaders());
            LOGGER.info(response.getBody().toString());
        } catch (Exception e) {
            Assert.fail("Exception retrieving questions " + e.getMessage());
        }
    }

    private QaUserAndRoleDto getRequest() {
        ObjectMapper om = new ObjectMapper();
        QaUserAndRoleDto qaUserAndRoleDto = new QaUserAndRoleDto();
        QaUserDto userDto = new QaUserDto();
        userDto.setFirstName("Scott");
        userDto.setLastName("Thomson");
        userDto.setUserName("scotthmsn@hotmail.com");
        qaUserAndRoleDto.setUser(userDto);
        qaUserAndRoleDto.setRoleName("scotttestrole");
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, qaUserAndRoleDto);
            LOGGER.info(sw.toString());
        }
        catch (Exception e) {

        }
        return qaUserAndRoleDto;
    }

    private HttpHeaders getHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJtOVFpNFExcHU3V1NzbkNQYWh5QUk5SlgxaEJfNGN4S2ZuRmloZG1zb2o0In0.eyJqdGkiOiI2NGIwNzMyZS05Y2NlLTQ1MDktOTFiNy00MWNhM2Q4YWNlNzgiLCJleHAiOjE1Njg2MzcwNTYsIm5iZiI6MCwiaWF0IjoxNTY4NjM2NzU2LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvcWEtcG9ydGFsIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjkyYjkyODM2LTQ0ZmItNDVjMi04YmVkLTBhNGVmYjY4YjYwMSIsInR5cCI6IkJlYXJlciIsImF6cCI6InFhLXBvcnRhbC11aSIsIm5vbmNlIjoiYTI4OTAzNzYtYzc3Yi00YzljLThlY2QtZjAxYzM0MjgzZjFjIiwiYXV0aF90aW1lIjoxNTY4NjM2NzU0LCJzZXNzaW9uX3N0YXRlIjoiZDJmNzczMGMtODQxMS00YjJlLThhNWUtZDZkMzMyOTRkMGFiIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJ0cmFpbmluZy11c2VyIiwiY29ob3J0X0NJX0ludGFrZV8xIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6Im9wZW5pZCBlbWFpbCBwcm9maWxlIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiSmltIFRyYWluZWUxIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidHJhaW5lZTFAcWEuY29tIiwiZ2l2ZW5fbmFtZSI6IkppbSIsImZhbWlseV9uYW1lIjoiVHJhaW5lZTEifQ.0CRnciRgi8oXk6MX4_h7fXzeZNgL8d7PI5-6Yz4N8wL3uVjpMHTEnInlIp2wUX_Ai2r-KZ03t4K2bG6P7MLd5FuOtjlQ8VPWusZHMtDh43aI1EgEjPypWwR6iXG2H0vbrJgry56FXfKb3HYU3aPltgi7J6soj14YMkJIbZ8JH-FrjZqHYm_EIGg6O9W4oIphzI4YRwNnqd0J3lLfv7et-HomLQBPw92JnXh9uLQg7amvHs-MBasb7ZPPqc6CzhG0os-tM3om-SCb6irOab_BTE8shxsJ8VlxEEVP7yv9YujsokBROkjU88ctKudi2L3P0kKX180b2BS5MBG28O-ylw");
        return headers;
    }
}
