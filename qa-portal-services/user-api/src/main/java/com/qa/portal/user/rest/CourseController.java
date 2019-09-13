package com.qa.portal.user.rest;

import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.user.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService service;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getCoursesForTrainee(String username){
        return ResponseEntity.ok(this.service.getCoursesForTrainee(username));
    }
}