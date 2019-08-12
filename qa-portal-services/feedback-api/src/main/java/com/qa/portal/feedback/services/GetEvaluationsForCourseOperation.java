package com.qa.portal.feedback.services;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;

import org.bouncycastle.jcajce.provider.symmetric.util.BaseMac;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GetEvaluationsForCourseOperation {

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private BaseMapper baseMapper;
    
    public GetEvaluationsForCourseOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository,
    		BaseMapper baseMapper) {
    	this.baseMapper = baseMapper;
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
    }

    public List<CohortCourseEvaluationDto> getEvaluationsForCourse(int cohortCourseId) {
    	List<CohortCourseEvaluationEntity> evals = this.cohortCourseEvaluationRepository.findAll();
    	
    	return evals.stream()
    	.map(e -> baseMapper.mapObject(e, CohortCourseEvaluationDto.class))
    	.collect(Collectors.toList());
		
        // All DB access, mapping from Entity to DTO return the response to the service
        
      }
}
