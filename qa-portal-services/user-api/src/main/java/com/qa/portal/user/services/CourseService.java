package com.qa.portal.user.services;

import com.qa.portal.common.dto.CourseDto;
//import com.qa.portal.reflection.dto.CohortSummaryDto;
//import com.qa.portal.reflection.dto.ReflectionDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private GetTraineeCoursesOperation getTraineeCoursesOperation;

    public CourseService(GetTraineeCoursesOperation getTraineeCoursesOperation) {
        this.getTraineeCoursesOperation = getTraineeCoursesOperation;
    }

    @Transactional
    public List<CourseDto> getCoursesForTrainee(String username){
        return this.getTraineeCoursesOperation.getCoursesForTrainee(username);
    }
}
