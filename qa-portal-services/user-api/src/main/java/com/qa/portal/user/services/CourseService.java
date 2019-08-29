package com.qa.portal.user.services;

import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.common.dto.QaUserDto;
//import com.qa.portal.reflection.dto.CohortSummaryDto;
//import com.qa.portal.reflection.dto.ReflectionDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private GetCoursesForTraineeOperation getCoursesForTraineeOperation;

    public CourseService(GetCoursesForTraineeOperation getCoursesForTraineeOperation) {
        this.getCoursesForTraineeOperation = getCoursesForTraineeOperation;
    }

    @Transactional
    public List<CourseDto> getCoursesForTrainee(String username){
        return this.getCoursesForTraineeOperation.getCoursesForTrainee(username);
    }
}
