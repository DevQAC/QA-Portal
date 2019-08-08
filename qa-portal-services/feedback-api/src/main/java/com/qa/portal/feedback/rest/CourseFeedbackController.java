package com.qa.portal.feedback.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.feedback.services.CourseFeedbackService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseFeedbackController {

    private CourseFeedbackService service;

    private QaSecurityContext qaSecurityContext;

    public CourseFeedbackController(CourseFeedbackService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }
}
