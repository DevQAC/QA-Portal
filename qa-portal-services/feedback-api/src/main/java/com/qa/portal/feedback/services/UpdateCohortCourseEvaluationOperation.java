 package com.qa.portal.feedback.services;

 import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
 import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
 import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
 import org.springframework.stereotype.Component;

 @Component
public class UpdateCohortCourseEvaluationOperation {

	private CohortCourseEvaluationRepository cohortRepository;

	private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;

	 public UpdateCohortCourseEvaluationOperation(CohortCourseEvaluationRepository cohortRepository,
												  CohortCourseEvaluationMapper cohortCourseEvaluationMapper) {
		 this.cohortRepository = cohortRepository;
		 this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
	 }

	 public CohortCourseEvaluationDto updateCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		 return this.cohortCourseEvaluationMapper.mapToCohortCourseEvaluationDto(
		 		cohortCourseEvaluationMapper.updateCohortCourseEvaluationEntity(courseEvaluation));
	}
}
