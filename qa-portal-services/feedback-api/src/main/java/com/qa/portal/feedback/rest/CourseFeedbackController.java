package com.qa.portal.feedback.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.services.CourseFeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class CourseFeedbackController {

    private CourseFeedbackService service;

    private QaSecurityContext qaSecurityContext;

    @Autowired
    public CourseFeedbackController(CourseFeedbackService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }

    @GetMapping("{cohort_course_id}")
    public ResponseEntity<CohortCourseFeedbackDto> getCohortCourseFeedback(@PathVariable("cohort_course_id") Integer cohortCourseId) {
        return ResponseEntity.ok(service.getCohortCourseFeedbackDto(cohortCourseId));
    }
    
    @PostMapping
    public ResponseEntity<CohortCourseFeedbackDto> createFeedbackDto(@RequestBody CohortCourseFeedbackDto feedbackDto) {
    	return ResponseEntity.ok(this.service.createCourseFeedbackForCourse(feedbackDto));
    }
    
}
