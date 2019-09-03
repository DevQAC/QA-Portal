package com.qa.portal.cv.services;


import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.regex.PatternSyntaxException;

@Component
public class CvSearchOperation {

    private CvVersionRepository repo;


    public CvSearchOperation(CvVersionRepository repo) {
        super();
        this.repo = repo;
    }

    public List<CvVersion> findByCiteria(CvSearchCriteria criteria) {
//        try{
//            Query query = new Query();
//            query.addCriteria(Criteria.where("fullName").regex(toLikeRegex(like), "i"));
//            return mongoOperations.find(query, CvVersion.class);
//        } catch(PatternSyntaxException e) {
//            return Collections.emptyList();
//        }
   }
}
