package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.mapper.CohortCourseEvaluationMapper;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateCourseEvaluationOperation {
	
	private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;
	
	private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

	public CreateCourseEvaluationOperation(CohortCourseEvaluationMapper cohortCourseEvaluationMapper,
										   CohortCourseEvaluationRepository cohortCourseEvaluationRepository) {
		this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
		this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
	}

	public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		return this.cohortCourseEvaluationMapper
				.mapToQaCohortDto(this.cohortCourseEvaluationRepository.save(this.cohortCourseEvaluationMapper.mapToQaCohortEntity(courseEvaluation)));
	}
}
