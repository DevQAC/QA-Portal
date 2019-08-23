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
	
	
	public GetCurrentCvVersionOperation(CvVersionRepository repo) {
		super();
		this.repo = repo;
	}
	
	public List<CvVersion> getAll() {
		List<CvVersion> records = repo.findAll();
		return records;
	}
	
	public List<CvVersion> findFullName(String fullName) {
		List<CvVersion> n = repo.findByFullName(fullName);
		if(n == null) {
			return null; //!IMPORTANT - needs an exception handler here!
		} else {
			return n;			
		}
	}
	
	public List<CvVersion> findCurrentCvVersion(Integer versionNumber) {
		List<CvVersion> a = repo.findByVersionNumber(versionNumber);
		if (a.isEmpty()) {
			return null; //!IMPORTANT - needs an exception handler here!
		} else {
			CvVersion cv = a.get(versionNumber);
			return cv.getVersionNumber();
		}
	}

}
