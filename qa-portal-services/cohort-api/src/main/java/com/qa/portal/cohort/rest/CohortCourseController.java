package com.qa.portal.cohort.rest;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.cohort.services.CohortCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cohort")
public class CohortCourseController {

    private CohortCourseService cohortCourseService;

    private QaSecurityContext qaSecurityContext;

    @GetMapping("course/trainer")
    public ResponseEntity<List<CohortCourseDto>> getCohortCoursesForTrainer() {
        return ResponseEntity.ok(cohortCourseService.getCohortCoursesForTrainer(qaSecurityContext.getUserName()));
    }

    @GetMapping("courses/{id}")
    public ResponseEntity<List<CohortCourseDto>> getCohortCoursesForCohort(@PathVariable("id") Integer cohortId) {
        return ResponseEntity.ok(cohortCourseService.getCohortCoursesForCohort(cohortId));
    }
}
