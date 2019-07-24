package com.qa.portal.reflection.rest;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.qa.portal.reflection.dto.QuestionDto;
import com.qa.portal.reflection.service.QuestionService;

public class QuestionController {

//	4. Get Self Rating Questions for Cohort
//	5. Get Cohorts for Trainer
//	6. Get Trainees for Cohort

	private QuestionService service;

	public ResponseEntity<Set<QuestionDto>> getQuestionsForCohort(Integer cohortId) {
		return ResponseEntity.ok(service.getQuestionsForCohort(cohortId));
	}
}
