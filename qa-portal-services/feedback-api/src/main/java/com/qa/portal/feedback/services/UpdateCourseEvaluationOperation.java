 package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.mapper.CohortCourseEvaluationMapper;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.springframework.stereotype.Component;

 @Component
public class UpdateCourseEvaluationOperation {

	private CohortCourseEvaluationRepository cohortRepository;

	private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;

	 public UpdateCourseEvaluationOperation(CohortCourseEvaluationRepository cohortRepository,
											CohortCourseEvaluationMapper cohortCourseEvaluationMapper) {
		 this.cohortRepository = cohortRepository;
		 this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
	 }

	 public CohortCourseEvaluationDto updateCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		 CohortCourseEvaluationEntity evalToUpdate = this.cohortRepository.findById(courseEvaluation.getId())
	                .orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
		 CohortCourseEvaluationEntity entity = this.cohortCourseEvaluationMapper.mapToQaCohortEntity(courseEvaluation);
		 evalToUpdate.setCategoryResponses(entity.getCategoryResponses());
		 return this.cohortCourseEvaluationMapper.mapToQaCohortDto(this.cohortRepository.save(evalToUpdate));
	}
}
