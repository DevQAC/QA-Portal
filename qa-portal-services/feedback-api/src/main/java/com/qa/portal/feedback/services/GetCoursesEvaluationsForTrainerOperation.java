package com.qa.portal.feedback.services;

import java.util.List;

public class GetCoursesEvaluationsForTrainerOperation {
	
	CourseEvaluationService eval = new CourseEvaluationService();

	public List<CourseEvaluationDto> getCourseEvaluationsForTrainer(String username) {
		return this.eval.getCourseEvaluationsForTrainer(username);
	}
}
