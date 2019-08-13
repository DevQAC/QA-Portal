package com.qa.portal.feedback.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;

public class CreateCourseEvaluationOperation {
	
	private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;
	
	public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		
		
		CohortCourseEvaluationEntity entity = new CohortCourseEvaluationEntity();
		BeanUtils.copyProperties(courseEvaluation, entity);
		cohortCourseEvaluationRepository.save(entity);

//		entity. 
		
		return courseEvaluation;
		
	}
}
