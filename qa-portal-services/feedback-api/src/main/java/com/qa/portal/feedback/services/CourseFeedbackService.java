package com.qa.portal.feedback.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;

@Service
public class CourseFeedbackService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CourseFeedbackService.class);
	
	private CreateFeedbackOperation feedbackOp;
	
	@Autowired
	public CourseFeedbackService(CreateFeedbackOperation feedbackOp) {
		this.feedbackOp = feedbackOp;
	}

	@Transactional
	public CohortCourseFeedbackDto createCourseFeedbackForCourse(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
		try {
			return this.feedbackOp.createFeedbackForm(cohortCourseFeedbackDto);
		}
		catch (Exception e) {
			LOGGER.error("Exception creating feedback " + e.getMessage(), e) ;
			throw e;
		}
	}

}
