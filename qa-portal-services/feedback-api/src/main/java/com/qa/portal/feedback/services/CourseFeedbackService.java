package com.qa.portal.feedback.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;

@Service
public class CourseFeedbackService {
	
	CreateFeedbackOperation feedbackOp;
	
	@Transactional
	public CohortCourseFeedbackDto createCourseFeedbackForCourse(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
		return this.feedbackOp.createFeedbackForm(cohortCourseFeedbackDto);
	}

}
