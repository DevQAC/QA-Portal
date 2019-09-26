package com.qa.portal.cv.services;

import com.qa.portal.common.security.QaSecurityContext;
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

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CreateCvOperationTest {

	@InjectMocks
	private CreateCvOperation createService;
	
	@Mock
	private CvVersionRepository repo;

	@Mock
	private CvVersionValidator cvVersionValidator;

	@Mock
	private QaSecurityContext qaSecurityContext;

	private CvVersion cvVersion = new CvVersion();

	@Before
	public void init() {
		Mockito.when(qaSecurityContext.getFirstName()).thenReturn("Junit");
		Mockito.when(qaSecurityContext.getSurname()).thenReturn("Test");
		Mockito.when(qaSecurityContext.getUserName()).thenReturn("testUser");
		Mockito.when(qaSecurityContext.getCohorts()).thenReturn(getCohorts());
		Mockito.when(repo.save(cvVersion)).thenReturn(cvVersion);
		this.cvVersion = this.createService.createCv(cvVersion, qaSecurityContext);
	}
	
	@Test
	public void createCvVersionNumberTest() {
		Integer versionNumber = this.cvVersion.getVersionNumber();
		Boolean conditionMet = false;
		if(versionNumber == 1) {
			conditionMet = true;
		}
		assertTrue("Initial Version Number is not 1", conditionMet);
	}
	
	@Test
	public void createCvFullNameTest() {
		String fullName = this.cvVersion.getFullName();
		Boolean conditionMet = false;
		if(fullName.equals("Junit Test")) {
			conditionMet = true;
		}
		assertTrue("Fullname failed to set upon CV creation", conditionMet);
		
	}

	private Set<String> getCohorts() {
		Set<String> cohorts = new HashSet<>();
		cohorts.add("CI_Intake_1");
		return cohorts;
	}
}
