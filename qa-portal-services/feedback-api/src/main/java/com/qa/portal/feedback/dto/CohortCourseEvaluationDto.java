package com.qa.portal.feedback.dto;

import java.util.Date;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.dto.TrainerDto;

public class CohortCourseEvaluationDto {
    private Integer id;

    private TraineeDto trainee;

    private TrainerDto trainer;

    private CohortCourseDto cohortCourse;
     

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TraineeDto getTrainee() {
        return trainee;
    }
    
    public Date getCourseStartDate() {
    	return this.cohortCourse.getStartDate();
    }
    
    public Date getCourseEndDate() {
    	return this.cohortCourse.getEndDate();
    }

    public void setTrainee(TraineeDto trainee) {
        this.trainee = trainee;
    }

    public TrainerDto getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerDto trainer) {
        this.trainer = trainer;
    }

    public CohortCourseDto getCohortCourse() {
        return cohortCourse;
    }

    public void setCohortCourse(CohortCourseDto cohortCourse) {
        this.cohortCourse = cohortCourse;
    }
    
}
