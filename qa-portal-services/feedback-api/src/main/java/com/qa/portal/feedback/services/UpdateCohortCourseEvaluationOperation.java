 package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
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
		 CohortCourseEvaluationEntity evalToUpdate = this.cohortRepository.findById(courseEvaluation.getId())
	                .orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
		 CohortCourseEvaluationEntity entity = this.cohortCourseEvaluationMapper.mapToQaCohortCourseEvaluationEntity(courseEvaluation);
		 evalToUpdate.setCategoryResponses(entity.getCategoryResponses());
		 return this.cohortCourseEvaluationMapper.mapToQaCohortCourseEvaluationDto(this.cohortRepository.save(evalToUpdate));
	}
}
