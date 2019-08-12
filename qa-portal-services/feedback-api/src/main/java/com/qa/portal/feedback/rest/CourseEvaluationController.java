package com.qa.portal.feedback.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.services.CourseEvaluationService;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseEvaluationController {

    private CourseEvaluationService service;

    private QaSecurityContext qaSecurityContext;

    public CourseEvaluationController(CourseEvaluationService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }

    @GetMapping("/trainee/evaluation/{cohort_course_id}")
    public ResponseEntity<List<CohortCourseEvaluationDto>> getEvaluationsForCourse(@PathVariable("cohort_course_id") int cohortCourseId) {
        // Call course evaluation service to get evaluations for course
    	System.out.println("Reached");
    	service.getEvaluationsForCourse(cohortCourseId);
        // Remove this once service call has been added
        return ResponseEntity.ok(Collections.emptyList());
    }
}
