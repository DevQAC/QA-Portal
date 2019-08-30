package com.qa.portal.cv.services;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

import org.springframework.stereotype.Component;

@Component
public class CreateCvOperation {

	private CvVersionRepository repo;
	
	public CreateCvOperation(CvVersionRepository repo) {
		super();
		this.repo = repo;
	}
	
	public CvVersion createCv(CvVersion newCv) {
		
		newCv.setFullName();
		
		repo.save(newCv);
		
		return newCv;
	}
}
