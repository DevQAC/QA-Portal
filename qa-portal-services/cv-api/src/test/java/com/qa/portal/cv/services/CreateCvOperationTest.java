package com.qa.portal.cv.services;

import static org.junit.Assert.assertTrue;

import com.qa.portal.cv.domain.UserDetails;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@SpringBootTest
public class CreateCvOperationTest {

	@InjectMocks
	private CreateCvOperation createService;
	
	@Mock
	private CvVersionRepository repo;
	
	private CvVersion testData = new CvVersion();

	private UserDetails userDetails = new UserDetails();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		this.testData.setFirstName("JUnit");
		this.testData.setSurname("Test");
		this.testData = this.createService.createCv(testData, userDetails);
	}
	
	@Test
	public void createCvVersionNumberTest() {
		Integer versionNumber = this.testData.getVersionNumber();
		Boolean conditionMet = false;
		if(versionNumber == 1) {
			conditionMet = true;
		}
		assertTrue("Initial Version Number is not 1", conditionMet);
	}
	
	@Test
	public void createCvFullNameTest() {
		String fullName = this.testData.getFullName();
		Boolean conditionMet = false;
		if(fullName.equals("JUnit Test")) {
			conditionMet = true;
		}
		assertTrue("Fullname failed to set upon CV creation", conditionMet);
		
	}
	
	@Test
	public void createCvStatusTest() {
		String status = this.testData.getStatus();
		Boolean conditionMet = false;
		if(status.equals("In Progress")) {
			conditionMet = true;
		}
		assertTrue("Initial status is not \"In Progress\"", conditionMet);
	}
}
