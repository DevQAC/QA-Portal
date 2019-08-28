package com.qa.portal.cv.services;

import java.util.List;

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
	
	public CvVersion createCv(CvVersion newCv) {
		
		newCv.setFullName();
		
		repo.save(newCv);
		
		return newCv;
	}
	
	public CvVersion findByUserNameAndVersionNumberTest(String userName, int versionNumber) {
		return this.repo.findByUserNameAndVersionNumberAllIgnoreCase(userName, versionNumber);
	}
	
}
