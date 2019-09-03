package com.qa.portal.cv.services;

import static org.junit.Assert.assertSame;

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
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		
		this.testData.setFirstName("JUnit");
		this.testData.setSurname("Test");
		
		this.testData = this.createService.createCv(testData);
	}
	
	@Test
	public void createCvTestVersionNumber() {
		
		Integer versionNumber = this.testData.getVersionNumber();
		
		assertSame("Initial Version Number is 1", 1, versionNumber);

	}
	
//	Incomplete
//	@Test
//	public void createCvTestFullName() {
//		
//		String fullName = this.testData.getFullName();
//		
//		assertSame("Fullname is set upon CV creation", "JUnit Test", fullName);
//		
//	}
	
	@Test
	public void createCvTestStatus() {

		String status = this.testData.getStatus();
		
		assertSame("Initial status is \"In Progress\"", "In Progress", status);
		
	}
	
}
