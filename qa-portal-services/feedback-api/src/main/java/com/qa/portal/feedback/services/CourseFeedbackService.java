package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseFeedbackService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CourseFeedbackService.class);
	
	private CreateFeedbackOperation feedbackOp;

	private GetCohortCourseFeedbackOperation getCohortCourseFeedbackOperation;

	public CourseFeedbackService(CreateFeedbackOperation feedbackOp, GetCohortCourseFeedbackOperation getCohortCourseFeedbackOperation) {
		this.feedbackOp = feedbackOp;
		this.getCohortCourseFeedbackOperation = getCohortCourseFeedbackOperation;
	}

	@Transactional
	public CohortCourseFeedbackDto createCourseFeedbackForCourse(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
		LOGGER.info("feedbackDto " + cohortCourseFeedbackDto.toString());
		try {
			return this.feedbackOp.createFeedbackForm(cohortCourseFeedbackDto);
		}
		catch (Exception e) {
			LOGGER.error("Exception creating feedback " + e.getMessage(), e) ;
			throw e;
		}
	}

	public CohortCourseFeedbackDto getCohortCourseFeedbackDto(Integer cohortCourseId) {
		return getCohortCourseFeedbackOperation.getCohortCourseFeedback(cohortCourseId);
	}
}
