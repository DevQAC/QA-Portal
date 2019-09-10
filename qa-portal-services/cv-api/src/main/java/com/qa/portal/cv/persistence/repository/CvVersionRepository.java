package com.qa.portal.cv.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qa.portal.cv.domain.CvVersion;

public interface CvVersionRepository extends MongoRepository<CvVersion, String> {

	List<CvVersion> findByFullNameIgnoreCase(String fullName);
	
	List<CvVersion> findByUserNameIgnoreCase(String userName);

	CvVersion findByVersionNumber(Integer versionNumber);
	
	Optional<CvVersion> findByUserNameAndVersionNumberAllIgnoreCase(String userName, Integer versionNumber);
	
	List<CvVersion> findByUserNameAllIgnoreCaseOrderByVersionNumberAsc(String userName);
}
