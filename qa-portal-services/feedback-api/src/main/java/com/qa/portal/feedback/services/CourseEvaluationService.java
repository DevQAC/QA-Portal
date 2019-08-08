package com.qa.portal.feedback.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;

@Service
public class CourseEvaluationService {
	
	private GetCoursesEvaluationsForTrainerOperation eval;
	
	@Transactional
	public List<CohortCourseEvaluationDto> getCourseEvaluationsForTrainer(String userName) {
		return this.eval.getCourseEvaluationsForTrainer(userName);
	}
}
