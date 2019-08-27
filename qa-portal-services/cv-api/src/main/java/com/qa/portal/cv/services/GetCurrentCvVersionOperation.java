package com.qa.portal.cv.services;

import java.util.List;

import org.springframework.stereotype.Component;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@Component
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
	
	public List<CvVersion> GetCurrentCvVersion(Integer id, Integer versionNumber) {
		return null;
//		List<CvVersion> r = repo.(id)
//		List<CvVersion> r = repo.findByVersionNumber(versionNumber);
//		if(r == null) {
//			return null; //!IMPORTANT - needs an exception handler here!
//		} else {
//			return r;			
//		}
	}
	
	public Integer findByVersionNumber(Integer versionNumber) {
		List<CvVersion> a = repo.findByVersionNumber(versionNumber);
		if (a.isEmpty()) {
			return null; //!IMPORTANT - needs an exception handler here!
		} else {
			CvVersion cv = a.get(versionNumber);
			return cv.getVersionNumber();
		}
	}

}
