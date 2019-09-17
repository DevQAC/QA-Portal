package com.qa.portal.core.rest;

import com.qa.portal.core.service.CourseManagementService;
import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.common.security.QaSecurityContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseManagementController {

    private CourseManagementService courseManagementService;

    private QaSecurityContext qaSecurityContext;

    public CourseManagementController(CourseManagementService courseManagementService,
                                      QaSecurityContext qaSecurityContext) {
        this.courseManagementService = courseManagementService;
        this.qaSecurityContext = qaSecurityContext;
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDetails) {
        return ResponseEntity.ok(courseManagementService.createCourse(courseDetails));
    }

    @PutMapping
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDetails) {
        return ResponseEntity.ok(courseManagementService.updateCourse(courseDetails));
    }

    @DeleteMapping("{courseName}")
    public ResponseEntity<?> deleteCourse(@PathVariable("courseName") String courseName) {
        courseManagementService.deleteCourse(courseName);
        return ResponseEntity.ok().build();
    }
}
