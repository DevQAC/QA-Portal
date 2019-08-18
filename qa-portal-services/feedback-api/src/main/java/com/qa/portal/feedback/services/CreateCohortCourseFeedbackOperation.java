package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseFeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateCohortCourseFeedbackOperation {
	
	private CohortCourseFeedbackMapper cohortCourseFeedbackMapper;
	
	private CohortCourseFeedbackRepository cohortCourseFeedbackRepository;
	
	@Autowired
	public CreateCohortCourseFeedbackOperation(CohortCourseFeedbackMapper cohortCourseFeedbackMapper,
											   CohortCourseFeedbackRepository cohortCourseFeedbackRepository) {
		this.cohortCourseFeedbackMapper = cohortCourseFeedbackMapper;
		this.cohortCourseFeedbackRepository = cohortCourseFeedbackRepository;
	}
	
	public CohortCourseFeedbackDto createFeedbackForm(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
		return this.cohortCourseFeedbackMapper
				.mapToFeedbackDto(this.cohortCourseFeedbackRepository.save(this.cohortCourseFeedbackMapper.mapToCohortCourseFeedbackEntity(cohortCourseFeedbackDto)));
	}
}
