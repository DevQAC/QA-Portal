package com.qa.portal.feedback.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.services.CourseEvaluationService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courseEvaluation")
public class CourseEvaluationController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CourseEvaluationController.class);

    private CourseEvaluationService service;

    private QaSecurityContext context;

    public CourseEvaluationController(CourseEvaluationService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.context = qaSecurityContext;
    }
    
    @GetMapping("/trainer")
    public ResponseEntity<List<CohortCourseEvaluationDto>> getCourseEvaluationsForTrainer(String username) {
    	return ResponseEntity.ok(this.service.getCourseEvaluationsForTrainer(context.getUserName()));
    }
}
