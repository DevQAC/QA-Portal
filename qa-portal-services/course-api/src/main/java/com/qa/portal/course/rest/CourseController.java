package com.qa.portal.course.rest;

import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.course.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    private CourseService service;

    private QaSecurityContext qaSecurityContext;

    public CourseController(CourseService service, QaSecurityContext qaSecurityContext) {
        this.service = service;
        this.qaSecurityContext = qaSecurityContext;
    }

    @GetMapping("courses")
    public ResponseEntity<List<CourseDto>> getCourses() {
        return ResponseEntity.ok(service.getCourses());
    }
}
