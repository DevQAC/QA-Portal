package com.qa.portal.cv.services;

import org.springframework.stereotype.Component;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@Component
public class UpdateCvVersionOperation {
	
	private CvVersionRepository repo;
	
	public UpdateCvVersionOperation(CvVersionRepository repo) {
		super();
		this.repo = repo;
	}

	public CvVersion updateCv(CvVersion updatedCv) {
		
		updatedCv.setFullName();
		updatedCv.setStatus("In Progress");
		
		repo.save(updatedCv);
		
		return updatedCv;
	}
	
	public CvVersion submitCv(CvVersion submittedCv) {
		
		submittedCv.setFullName();
		submittedCv.setStatus("For Review");
		
		repo.save(submittedCv);
		
		return submittedCv;
	}
	
	public CvVersion approveCv(CvVersion submittedCv) {
		
		//ID should be set to null so a new entry is created and version number should be incremented.
		
		submittedCv.setFullName();
		submittedCv.setStatus("Approved");
		
		repo.save(submittedCv);
		
		return submittedCv;
	}
	
	public CvVersion failCv(CvVersion submittedCv) {
		
		submittedCv.setFullName();
		submittedCv.setStatus("Failed Review");
		
		repo.save(submittedCv);
		
		return submittedCv;
	}
	
}
