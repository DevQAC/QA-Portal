package com.qa.portal.feedback.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.portal.feedback.rest.CourseEvaluationController;

@Service
public class CourseEvaluationService {
	
	CourseEvaluationController controller = new CourseEvaluationController(null, null);
	
	public List<CourseEvaluationDto> getCourseEvaluationsForTrainer(String username) {
		return controller.getCourseEvaluationsForTrainer(username);
		
	}
}
