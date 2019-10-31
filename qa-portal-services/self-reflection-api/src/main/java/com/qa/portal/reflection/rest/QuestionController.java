package com.qa.portal.reflection.rest;

import java.util.Set;

import com.qa.portal.common.dto.QuestionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.portal.reflection.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	private QuestionService service;

	public QuestionController(QuestionService service) {
		super();
		this.service = service;
	}

	@GetMapping("/cohort/{id}")
	public ResponseEntity<Set<QuestionDto>> getQuestionsForCohort(@PathVariable("id") Integer cohortId) {
		return ResponseEntity.ok(service.getQuestionsForCohort(cohortId));
	}
}
