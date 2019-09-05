package com.qa.portal.cv.services;

import com.qa.portal.common.security.QaSecurityContext;
import org.springframework.stereotype.Component;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@Component
public class UpdateCvVersionOperation {
	
	private CvVersionRepository repo;
	private QaSecurityContext securityContext;

	public UpdateCvVersionOperation(CvVersionRepository repo, QaSecurityContext securityContext) {
		this.repo = repo;
		this.securityContext = securityContext;
	}

	public CvVersion updateCv(CvVersion updatedCv) {
        updatedCv.setFirstName(securityContext.getFirstName());
        updatedCv.setSurname(securityContext.getSurname());
        updatedCv.setFullName();

		updatedCv.setStatus("In Progress");
		repo.save(updatedCv);
		return updatedCv;
	}

	public CvVersion submitCv(CvVersion submittedCv) {

		submittedCv.setStatus("For Review");
		repo.save(submittedCv);
		return submittedCv;
	}

	public CvVersion approveCv(CvVersion submittedCv) {
		//ID should be set to null so a new entry is created and version number should be incremented.

		submittedCv.setStatus("Approved");
		repo.save(submittedCv);
		return submittedCv;
	}
	
	public CvVersion failCv(CvVersion submittedCv) {

		submittedCv.setStatus("Failed Review");
		repo.save(submittedCv);
		return submittedCv;
	}
}
