package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.course.CourseManagementService;
import com.qa.portal.common.dto.CourseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class CourseManagementController {

    private CourseManagementService courseManagementService;

    public CourseManagementController(CourseManagementService courseManagementService) {
        this.courseManagementService = courseManagementService;
    }

    @PostMapping("/course")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseManagementService.createCourse(courseDto));
    }

    @PutMapping("/course")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto) {
        return ResponseEntity.ok(courseManagementService.updateCourse(courseDto));
    }
}
