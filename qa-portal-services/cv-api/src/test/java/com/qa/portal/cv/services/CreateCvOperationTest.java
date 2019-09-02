package com.qa.portal.cv.services;

import static org.mockito.Mockito.verify;

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
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
//	Incomplete
	
	@Test
	public void createCvTest() {
		
		CvVersion testData = new CvVersion();
		
		testData.setFirstName("JUnit");
		testData.setSurname("Test");
		
		testData = this.createService.createCv(testData);
		
		verify(repo);
		
	}
	
}
