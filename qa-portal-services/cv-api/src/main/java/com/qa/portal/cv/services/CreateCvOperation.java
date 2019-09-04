package com.qa.portal.cv.services;

import com.qa.portal.cv.domain.CvUserDetails;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreateCvOperation {

	private CvVersionRepository repo;
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateCvOperation.class);
	
	public CreateCvOperation(CvVersionRepository repo) {
		super();
		this.repo = repo;
	}

	// pass in current username, cohort and full name from security context
	public CvVersion createCv(CvVersion newCv, CvUserDetails user) {

		newCv.setUserName(user.getUserName());
		newCv.setFirstName(user.getFirstName());
		newCv.setSurname(user.getLastName());
		newCv.setFullName(); // generated from first and last

		newCv.setCohort(user.getCohort());
		newCv.setStatus("In Progress");

		repo.save(newCv);
		
		return newCv;
	}
}
