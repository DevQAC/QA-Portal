package com.qa.portal.feedback.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.feedback.services.CourseEvaluationDto;
import com.qa.portal.feedback.services.CourseEvaluationService;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseEvaluationController {

    private CourseEvaluationService service;

    private QaSecurityContext qaSecurityContext;

    public CourseEvaluationController(CourseEvaluationService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }
    
    public List<CourseEvaluationDto> getCourseEvaluationsForTrainer(String username) {
    	return null;
    }
}
