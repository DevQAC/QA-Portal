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

@Component
public class CvSearchOperation {

    private CvVersionRepository repo;

    private MongoOperations mongoOperations;

    public CvSearchOperation(CvVersionRepository repo, MongoOperations mongoOperations) {
        super();
        this.repo = repo;
        this.mongoOperations = mongoOperations;
    }


    // looking for a technology in one of several nested arrays is hardcoded
    // and should probably be done differently.
    // findByCriteria retrieves all cvs which meet the criteria selected on the admin search page
    public List<CvVersion> findByCriteria(CvSearchCriteria criteria) {
        try{
           Query query = new Query();
            if (!criteria.getCohort().equals("")) {
                query.addCriteria(Criteria.where("cohort").is(criteria.getCohort()));
            }
            if (!criteria.getFullName().equals("")) {
                query.addCriteria(Criteria.where("fullName").is(criteria.getFullName()));
            }
            if (!criteria.getCvStatus().equals("")) {
                query.addCriteria(Criteria.where("status").is(criteria.getCvStatus()));
            }
            // next one may need more thought, value in an array of objects
            // query.addCriteria(Criteria.where(("allSkills.other").(criteria.getTechnology())
            if (!criteria.getTechnology().equals("")) {
                query.addCriteria(Criteria.where("allSkills").exists(true).orOperator(
                        Criteria.where("allSkills.other").is(criteria.getTechnology()),
                        Criteria.where("allSkills.programmingLanguages").is(criteria.getTechnology()),
                        Criteria.where("allSkills.ides").is(criteria.getTechnology()),
                        Criteria.where("allSkills.operatingSystems").is(criteria.getTechnology()),
                        Criteria.where("allSkills.devops").is(criteria.getTechnology()),
                        Criteria.where("allSkills.databases").is(criteria.getTechnology()),
                        Criteria.where("allSkills.platforms").is(criteria.getTechnology())

                        )
                );
            }
            return mongoOperations.find(query, CvVersion.class);
        } catch(Exception e) {
            return Collections.emptyList();
        }
   }
}
