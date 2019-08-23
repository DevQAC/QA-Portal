package com.qa.portal.cv.services;

import org.springframework.stereotype.Component;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@Component
public class CreateCvOperation {

	private CvVersionRepository repo;
	
	public CreateCvOperation(CvVersionRepository repo) {
		super();
		this.repo = repo;
	}
	
	public String createCv(CvVersion newCv) {
		
		newCv.setFullName();
		
		repo.save(newCv);
		
		return "CV successfully added";
	}
	
}
