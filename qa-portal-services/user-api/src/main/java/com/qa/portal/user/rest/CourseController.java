package com.qa.portal.user.rest;

//import com.qa.portal.common.dto.QaUserDto;
//import com.qa.portal.common.security.QaSecurityContext;
//import com.qa.portal.reflection.dto.CohortSummaryDto;
//import com.qa.portal.reflection.dto.ReflectionDto;
//import com.qa.portal.reflection.service.ReflectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService service;

    @GetMapping
    public ResponseEntity<List<CourseDto>> getCoursesForTrainee(String username){
        return ResponseEntity.ok(this.service.getCoursesForTrainee());
    }
}