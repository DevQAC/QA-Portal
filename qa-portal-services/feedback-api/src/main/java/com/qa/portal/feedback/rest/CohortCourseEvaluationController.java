package com.qa.portal.feedback.rest;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.services.CohortCourseEvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
public class CohortCourseEvaluationController {

    private final Logger LOGGER = LoggerFactory.getLogger(CohortCourseEvaluationController.class);

    private CohortCourseEvaluationService service;

    private QaSecurityContext qaSecurityContext;

    public CohortCourseEvaluationController(CohortCourseEvaluationService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }

    @GetMapping("/trainee")
    public ResponseEntity<List<CohortCourseEvaluationDto>> getCohortCourseEvaluationsForTrainee() {
        return ResponseEntity.ok(service.getCohortCourseEvaluationsForTrainee(qaSecurityContext.getUserName()));
    }

    @GetMapping("/trainee/current")
    public ResponseEntity<CohortCourseEvaluationDto> getCurrentEvaluationForTrainee() {
        return ResponseEntity.ok(service.getCurrentEvaluationForTrainee(qaSecurityContext.getUserName()));
    }

    @GetMapping("/trainer")
    public ResponseEntity<List<CohortCourseDto>> getCohortCoursesForTrainer() {
        return ResponseEntity.ok(this.service.getCohortCoursesForTrainer(qaSecurityContext.getUserName()));
    }

    @GetMapping("{id}")
    public ResponseEntity<CohortCourseEvaluationDto> getCohortCourseEvaluation(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getCohortCourseEvaluation(id));
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<List<CohortCourseEvaluationDto>> getCohortCourseEvaluationsForCourse(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(service.getCohortCourseEvaluationsForCourse(id));
    }

    @PostMapping("/course")
    public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
        return service.createCourseEvaluationForTrainee(courseEvaluation);
    }

    @PutMapping("/course")
    public CohortCourseEvaluationDto updateCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
        return service.updateCourseEvaluationForTrainee(courseEvaluation);
    }
}
