package com.qa.portal.feedback.rest;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.services.CourseEvaluationService;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
@RestController
@RequestMapping("/course-evaluation")
public class CourseEvaluationController {

	private final Logger LOGGER = LoggerFactory.getLogger(CourseEvaluationController.class);

    private CourseEvaluationService service;

    private QaSecurityContext context;

    public CourseEvaluationController(CourseEvaluationService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.context = qaSecurityContext;
    }

    @GetMapping("/trainer")
    public ResponseEntity<List<CohortCourseDto>> getCourseEvaluationsForTrainer(String username) {
    	return ResponseEntity.ok(this.service.getCourseEvaluationsForTrainer(context.getUserName()));
    }

    @GetMapping("/trainee/evaluation/{cohort_course_id}")
    public ResponseEntity<List<CohortCourseEvaluationDto>> getEvaluationsForCourse(@PathVariable("cohort_course_id") int cohortCourseId) {
        // Call course evaluation service to get evaluations for course
    	service.getEvaluationsForCourse(cohortCourseId);
        // Remove this once service call has been added
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/course/evaluation/{id}")
    public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		return service.createCourseEvaluation(courseEvaluation);
    }

    @PutMapping("/course/evaluation")
    public CohortCourseEvaluationDto updateCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
    	return service.updateCourseEvaluation(courseEvaluation);
    }

}
