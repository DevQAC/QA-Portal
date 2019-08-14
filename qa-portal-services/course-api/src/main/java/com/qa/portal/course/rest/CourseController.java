package com.qa.portal.course.rest;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.course.services.CourseService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {

    private CourseService service;

    private QaSecurityContext qaSecurityContext;

    public CourseController(CourseService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }
}
