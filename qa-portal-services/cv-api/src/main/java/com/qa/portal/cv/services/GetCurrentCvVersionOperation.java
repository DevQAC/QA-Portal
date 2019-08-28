package com.qa.portal.cv.services;

import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

@Component
public class GetCurrentCvVersionOperation {

	private MongoOperations mongoOperations;
	private CvVersionRepository repo;	
	
	public GetCurrentCvVersionOperation(CvVersionRepository repo, MongoOperations mongoOperations) {
		super();
		this.repo = repo;
		this.mongoOperations = mongoOperations;
	}
	
	public List<CvVersion> getAll() {
		List<CvVersion> records = repo.findAll();
		return records;
	}
	
	public List<CvVersion> findFullNameIgnoreCase(String like) {		
		try{
            Query query = new Query();
            query.addCriteria(Criteria.where("fullName").regex(toLikeRegex(like), "i"));
            return mongoOperations.find(query, CvVersion.class);
        } catch(PatternSyntaxException e) {
            return Collections.emptyList();
        }
		
	}
	private String toLikeRegex(String source) {
        return source.replaceAll("\\*", ".*");
    }

	public CvVersion findByVersionNumber(Integer versionNumber) {
		CvVersion a = repo.findByVersionNumber(versionNumber);
		return a;
	}
	
}
