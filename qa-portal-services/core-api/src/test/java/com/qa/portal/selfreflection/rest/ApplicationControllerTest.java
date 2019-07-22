package com.qa.portal.selfreflection.rest;

//import java.util.List;
//import com.qa.portal.CoreApiApplication;
//import com.qa.portal.core.dto.DepartmentApplicationsDto;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@SpringBootTest(classes = CoreApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationControllerTest {

//    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationControllerTest.class);
//
//    private ApplicationControllerTestHelper testHelper = new ApplicationControllerTestHelper();
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private HttpHeaders httpHeaders = new HttpHeaders();
//
//    @Test
//    public void testGetApplicationsByDepartment() {
//        try {
//            ResponseEntity<List<DepartmentApplicationsDto>> savedUser = restTemplate.getForEntity(createURLWithPort("/admin-api/applications"), List.class);
//            LOGGER.debug("Saved user " + savedUser);
//        }
//        catch (Exception e) {
//            LOGGER.debug("Exception " + e.getMessage(), e);
//        }
//    }
//
//    private String createURLWithPort(String uri) {
//        return "http://localhost:" + port + uri;
//    }
}
