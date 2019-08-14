package com.qa.portal.user.services;

import com.qa.portal.common.dto.CourseDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class GetCoursesForTraineeOperation {

    List<CourseDto> getCoursesForTrainee(String username){
        return Collections.emptyList();
    }
}