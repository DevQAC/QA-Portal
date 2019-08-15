 package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.mapper.CohortMapper;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.springframework.stereotype.Component;

 @Component
public class UpdateCourseEvaluationOperation {

	private CohortCourseEvaluationRepository cohortRepository;

	private CohortMapper cohortMapper;

	 public UpdateCourseEvaluationOperation(CohortCourseEvaluationRepository cohortRepository,
											CohortMapper cohortMapper) {
		 this.cohortRepository = cohortRepository;
		 this.cohortMapper = cohortMapper;
	 }

	 public CohortCourseEvaluationDto updateCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		 CohortCourseEvaluationEntity evalToUpdate = this.cohortRepository.findById(courseEvaluation.getId())
	                .orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
		 CohortCourseEvaluationEntity entity = this.cohortMapper.mapToQaCohortEntity(courseEvaluation);
		 evalToUpdate.setCategoryResponses(entity.getCategoryResponses());
		 return this.cohortMapper.mapToQaCohortDto(this.cohortRepository.save(evalToUpdate));
	}
}
