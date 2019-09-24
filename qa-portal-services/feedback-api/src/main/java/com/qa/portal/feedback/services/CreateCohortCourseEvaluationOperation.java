package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateCohortCourseEvaluationOperation {
	
	private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;
	
	private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

	public CreateCohortCourseEvaluationOperation(CohortCourseEvaluationMapper cohortCourseEvaluationMapper,
												 CohortCourseEvaluationRepository cohortCourseEvaluationRepository) {
		this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
		this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
	}

	public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation,
															String traineeUserName) {
		return this.cohortCourseEvaluationMapper
				.mapToCohortCourseEvaluationDto(this.cohortCourseEvaluationRepository
								.save(this.cohortCourseEvaluationMapper.createCohortCourseEvaluationEntity(courseEvaluation, traineeUserName)));
	}
}
