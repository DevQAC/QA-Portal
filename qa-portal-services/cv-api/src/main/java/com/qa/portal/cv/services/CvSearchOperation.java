package com.qa.portal.cv.services;


import com.qa.portal.cv.domain.CvSearchCriteria;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

@Component
public class CvSearchOperation {

    private CvVersionRepository repo;
    private MongoOperations mongoOperations;


    public CvSearchOperation(CvVersionRepository repo, MongoOperations mongoOperations) {
        super();
        this.repo = repo;
        this.mongoOperations = mongoOperations;
    }

    public List<CvVersion> findByCriteria(CvSearchCriteria criteria) {
        try{
            Query query = new Query();
            if (criteria.getCohort() != "") {
                query.addCriteria(Criteria.where("cohort").is(criteria.getCohort()));
            }
            if (criteria.getFullName() !=  "") {
                query.addCriteria(Criteria.where("fullName").is(criteria.getFullName()));
            }
            if (criteria.getCvStatus() != "") {
                query.addCriteria(Criteria.where("status").is(criteria.getCvStatus()));
            }
            // next one may need more thought, value in an array of objects
            if (criteria.getTechnology() != "") {
                query.addCriteria(Criteria.where("allSkills").is(criteria.getTechnology()));
            }
            return mongoOperations.find(query, CvVersion.class);
        } catch(PatternSyntaxException e) {
            return Collections.emptyList();
        }


   }
}
