package com.qa.portal.cv.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@Component
public class GetCurrentCvVersionOperation {

	private CvVersionRepository repo;
	
	private Comparator<CvVersion> cvComparator = 
			(cv1, cv2) -> cv1.getVersionNumber() - cv2.getVersionNumber();
	
	
	public GetCurrentCvVersionOperation(CvVersionRepository repo) {
		super();
		this.repo = repo;
	}
	
	public List<CvVersion> getAll() {
		List<CvVersion> records = repo.findAll();
		return records;
	}
	
	public List<CvVersion> findFullNameIgnoreCase(String fullName) {		
		List<CvVersion> n = repo.findByFullNameIgnoreCase(fullName);
//		Collections.sort(n, cvComparator);
		if(n == null) {
			return null; //!IMPORTANT - needs an exception handler here!
		} else {
			return n;			
		}
	
	}
	
//	public CvVersion getCurrent(Integer versionNumber) {
//		CvVersion cv = repo.getCurrent(versionNumber);
//		return cv;
//	}
	
//	public Integer findByVersionNumber(Integer versionNumber) {
//		List<CvVersion> a = repo.findByVersionNumber(versionNumber);
//		if (a.isEmpty()) {
//			return null; //!IMPORTANT - needs an exception handler here!
//		} else {
//			CvVersion cv = a.get(versionNumber);
//			return cv.getVersionNumber();
//		}
//	}

}
