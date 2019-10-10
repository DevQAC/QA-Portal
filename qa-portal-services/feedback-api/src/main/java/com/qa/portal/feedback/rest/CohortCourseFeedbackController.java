package com.qa.portal.feedback.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.services.CohortCourseFeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class CohortCourseFeedbackController {

    private final Logger LOGGER = LoggerFactory.getLogger(CohortCourseFeedbackController.class);

    private CohortCourseFeedbackService service;

    private QaSecurityContext qaSecurityContext;

    public CohortCourseFeedbackController(CohortCourseFeedbackService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CohortCourseFeedbackDto> getCohortCourseFeedback(@PathVariable("id") Integer cohortCourseFeedbackId) {
        return ResponseEntity.ok(service.getCohortCourseFeedback(cohortCourseFeedbackId));
    }

    @GetMapping("/trainer")
    public ResponseEntity<List<CohortCourseFeedbackDto>> getCohortCourseFeedbacksForTrainer() {
        return ResponseEntity.ok(service.getCohortCourseFeedbacksForTrainer(qaSecurityContext.getUserName()));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<CohortCourseFeedbackDto> getCohortCourseFeedbackForCourse(@PathVariable("id") Integer cohortCourseId) {
        return ResponseEntity.ok(service.getCohortCourseFeedbackForCourse(cohortCourseId));
    }

    @PostMapping
    public ResponseEntity<CohortCourseFeedbackDto> createCohortCourseFeedback(@RequestBody CohortCourseFeedbackDto feedbackDto) {
        return ResponseEntity.ok(this.service.createCohortCourseFeedback(feedbackDto));
    }

    @PutMapping
    public ResponseEntity<CohortCourseFeedbackDto> updateCohortCourseFeedback(@RequestBody CohortCourseFeedbackDto feedbackDto) {
        return ResponseEntity.ok(this.service.updateCohortCourseFeedback(feedbackDto));
    }
}
