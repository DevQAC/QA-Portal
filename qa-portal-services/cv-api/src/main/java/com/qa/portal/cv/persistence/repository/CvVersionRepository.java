package com.qa.portal.cv.persistence.repository;

import com.qa.portal.cv.domain.CvVersion;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CvVersionRepository extends MongoRepository<CvVersion, Integer> {

	public List<CvVersion> findByFullName(String fullName);

	public List<CvVersion> findByVersionNumber(Integer versionNumber);
	
	@Query("{ $last : versionNumber }")
	public List<CvVersion> getCurrent(Integer versionNumber);
}