package com.qa.portal.cv.persistence.repository;

import com.qa.portal.cv.domain.CvVersion;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CvVersionRepository extends MongoRepository<CvVersion, Integer> {


	public List<CvVersion> findByVersionNumber(Integer versionNumber);

}


