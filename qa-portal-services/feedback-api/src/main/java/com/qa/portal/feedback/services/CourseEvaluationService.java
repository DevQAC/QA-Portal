package com.qa.portal.feedback.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;

@Service
public class CourseEvaluationService {
	
	//List<CourseEvaluationDto> getEvaluationsForCourse(CourseDto)
	public CreateCourseEvaluationOperation createCourseEvaluation = new CreateCourseEvaluationOperation();

	public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		return createCourseEvaluation.createCourseEvaluation(courseEvaluation);
	}
}
