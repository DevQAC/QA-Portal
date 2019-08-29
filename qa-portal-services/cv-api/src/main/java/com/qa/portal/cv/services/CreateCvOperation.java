package com.qa.portal.cv.services;

import java.util.Optional;

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
	
//	The following method loops through the database and checks to see if the submitted version number already exists.
//	Trying a different approach but left the code as it might be useful for reference.
	
//	public void generateVersionIfExists(CvVersion newCv) {
//		
//		String userName = newCv.getUserName();
//		int versionNumber = newCv.getVersionNumber();
//		
//		Boolean breakCondition = false;
//		
//		while(!breakCondition) {
//			
//			Optional<CvVersion> temp = this.repo.findByUserNameAndVersionNumberAllIgnoreCase(userName, versionNumber);
//			
//			if(temp.isPresent()) {
//				versionNumber++;
//			} else {
//				breakCondition = true;
//			}
//			
//		}
//		
//		newCv.setVersionNumber(versionNumber);
//
//	}
	
}
