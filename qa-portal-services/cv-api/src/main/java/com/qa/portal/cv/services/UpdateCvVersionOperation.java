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

	public String updateCv(CvVersion updatedCv) {
		
		repo.save(updatedCv);
		
		return "CV Updated";
	}
	
}
