package com.qa.portal.cv.services;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@SpringBootTest
public class UpdateCvOperationTest {

	@InjectMocks
	private CreateCvOperation createService;
	
	@InjectMocks
	private UpdateCvVersionOperation updateService;
	
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
	public void updateCvFullNameTest() {
		
		this.testData.setFirstName("Update");
		this.testData.setSurname("Test");
		
		this.testData = this.updateService.updateCv(testData);
		
		String fullName = this.testData.getFullName();
		
		boolean conditionMet = false;
		
		if(fullName.equals("Update Test")) {
			conditionMet = true;
		}
		
		assertTrue("Fullname failed to set during the update operation", conditionMet);
		
	}
	
	@Test
	public void updateCvStatusTest() {
		
		this.testData.setStatus("For Review");
		
		this.testData = this.updateService.updateCv(testData);

		String status = this.testData.getStatus();
		
		boolean conditionMet = false;
		
		if(status.equals("For Review")) {
			conditionMet = true;
		}
		
		assertTrue("The status changed during the update operation, "
				+ "which should only happen if the updated cv has a status of \"Approved\"", conditionMet);
		
	}
	
}
