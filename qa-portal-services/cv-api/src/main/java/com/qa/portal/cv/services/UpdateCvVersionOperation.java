package com.qa.portal.cv.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@Component
public class UpdateCvVersionOperation {
	
	private CvVersionRepository repo;
	
	public UpdateCvVersionOperation(CvVersionRepository repo) {
		super();
		this.repo = repo;
	}

	public CvVersion updateCv(CvVersion updatedCv) {

		updatedCv.setFullName();
		
		if(updatedCv.getStatus().equals("Approved")) {
			
			// The approved CV is stored.
			
			repo.save(updatedCv);
			
			// The following steps generate a new version to be worked on.
			
			updatedCv.setId(null);
			
			// The status is set to "In Progress" as edit permissions are dependent on the status.
			
			updatedCv.setStatus("In Progress");
			
			List<CvVersion> allVersions = repo.findByUserNameAllIgnoreCaseOrderByVersionNumberAsc(updatedCv.getUserName());
			
			int newVersionNumber = (allVersions.get(allVersions.size() - 1).getVersionNumber()) + 1;
			updatedCv.setVersionNumber(newVersionNumber);
			
			repo.save(updatedCv);
			return updatedCv;
		}
		
		repo.save(updatedCv);
		return updatedCv;
	}
	
}
