package com.qa.portal.cv.persistence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.qa.portal.cv.domain.CvVersion;

public interface CvVersionRepository extends MongoRepository<CvVersion, Integer> {

	public List<CvVersion> findByFullNameIgnoreCase(String fullName);
	
	public List<CvVersion> findByUserNameIgnoreCase(String userName);

	public CvVersion findByVersionNumber(Integer versionNumber);
	
	public CvVersion findByUserNameAndVersionNumberAllIgnoreCase(String userName, Integer versionNumber);
	
}