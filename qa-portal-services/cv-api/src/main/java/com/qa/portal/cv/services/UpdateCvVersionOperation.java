package com.qa.portal.cv.services;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.domain.validator.CvVersionValidator;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class UpdateCvVersionOperation {

	private final Logger LOGGER = LoggerFactory.getLogger(UpdateCvVersionOperation.class);

	private CvVersionRepository repo;

	private CvVersionValidator cvVersionValidator;

	public UpdateCvVersionOperation(CvVersionRepository repo, CvVersionValidator cvVersionValidator) {
		this.repo = repo;
		this.cvVersionValidator = cvVersionValidator;
	}

	public CvVersion updateCv(CvVersion updatedCv) {
		cvVersionValidator.validateCvVersion(updatedCv);
		return repo.save(updatedCv);
	}
}
