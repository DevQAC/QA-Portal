package com.qa.portal.cv.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@Service
@Transactional
public class GetCurrentCvVersionOperation {

	private CvVersionRepository repo;
	
	public GetCurrentCvVersionOperation() {
		
	}
	
	public String findVersion(Integer versionNumber) {
		List<CvVersion> a = repo.findByVersionNumber(versionNumber);
		if (a.isEmpty()) {
			return "Cannot find this CV.";
		} else {
			CvVersion cv = a.get(versionNumber);
			return "Record found: " + cv.getVersionNumber();
		}
	}

}
