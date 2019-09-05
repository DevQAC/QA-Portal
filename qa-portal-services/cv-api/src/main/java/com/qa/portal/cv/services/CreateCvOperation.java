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

	// pass in current username from security context
	public CvVersion createCv(CvVersion newCv, String userName) {

		newCv.setUserName(userName);
		newCv.setFullName();
		
		repo.save(newCv);
		
		return newCv;
	}
}
