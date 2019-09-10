package com.qa.portal.cv.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class CreateCvOperation {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateCvOperation.class);

	private CvVersionRepository repo;

	public CreateCvOperation(CvVersionRepository repo) {
		super();
		this.repo = repo;
	}

	public CvVersion createCv(CvVersion newCv, QaSecurityContext qaSecurityContext) {
		newCv.setUserName(qaSecurityContext.getUserName());
		newCv.setFirstName(qaSecurityContext.getFirstName());
		newCv.setSurname(qaSecurityContext.getSurname());
		newCv.setCohort(getCohort(qaSecurityContext));
		newCv.setVersionNumber(getPreviousVersion(qaSecurityContext) + 1);
		return repo.save(newCv);
	}

	private String getCohort(QaSecurityContext qaSecurityContext) {
		return qaSecurityContext.getCohorts().stream()
				.findFirst().orElseThrow(() -> new QaPortalBusinessException("Trainee does not have a cohort"));
	}

	private Integer getPreviousVersion(QaSecurityContext qaSecurityContext) {
		return repo.findByUserNameIgnoreCase(qaSecurityContext.getUserName())
				.stream()
				.map(cv -> cv.getVersionNumber())
				.max(Comparator.comparingInt(v -> v))
				.orElseGet(() -> Integer.valueOf(0));
	}
}
