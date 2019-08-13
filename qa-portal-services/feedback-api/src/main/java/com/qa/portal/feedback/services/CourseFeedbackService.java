package com.qa.portal.feedback.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;

@Service
public class CourseFeedbackService {
	
	CreateFeedbackOperation feedbackOp;
	
	public List<CohortCourseFeedbackDto> getCourseFeedbackForCourse(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
		return this.feedbackOp.createFeedbackForm(cohortCourseFeedbackDto);
	}

}
