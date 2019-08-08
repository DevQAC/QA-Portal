package com.qa.portal.feedback.services;

import java.util.List;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;

public class GetCoursesEvaluationsForTrainerOperation {
	
	CourseEvaluationService eval = new CourseEvaluationService();

	public List<CohortCourseEvaluationDto> getCourseEvaluationsForTrainer(String userName) {
		return this.eval.getCourseEvaluationsForTrainer(userName);
	}
}
