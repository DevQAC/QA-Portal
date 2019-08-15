package com.qa.portal.feedback.services;


import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.mapper.CohortCourseMapper;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;


public class CreateCourseEvaluationOperation {
	
	private CohortCourseMapper cohortMapper;
	
	private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;
	
	public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		
		
		return this.cohortMapper
				.mapToQaCohortDto(this.cohortCourseEvaluationRepository.save(this.cohortMapper.mapToQaCohortEntity(courseEvaluation)));
	}
}
