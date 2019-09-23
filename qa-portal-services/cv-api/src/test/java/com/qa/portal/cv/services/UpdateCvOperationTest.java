package com.qa.portal.cv.services;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.domain.validator.CvVersionValidator;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UpdateCvOperationTest {

	@InjectMocks
	private UpdateCvVersionOperation updateService;

	@Mock
	private CvVersionValidator cvVersionValidator;
	
	@Mock
	private CvVersionRepository repo;

	private CvVersion cvVersion = new CvVersion();
	
	@Before
	public void init() {
		cvVersion.setFirstName("Junit");
		cvVersion.setSurname("Test");
		cvVersion.setCohort(getCohort());
		Mockito.when(repo.save(cvVersion)).thenReturn(cvVersion);
		this.cvVersion = this.updateService.updateCv(cvVersion);
	}
	
	@Test
	public void updateCvFullNameTest() {
		this.cvVersion.setFirstName("Update");
		this.cvVersion.setSurname("Test");
		this.cvVersion = this.updateService.updateCv(cvVersion);
		String fullName = this.cvVersion.getFullName();
		boolean conditionMet = false;
		if(fullName.equals("Update Test")) {
			conditionMet = true;
		}
		assertTrue("Fullname failed to set during the update operation", conditionMet);
	}

	private String getCohort() {
		return "CI_Intake_1";
	}
}
