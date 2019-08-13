package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.dto.TrainerDto;

import java.util.List;

public class CohortCourseEvaluationDto {
    private Integer id;

    private TraineeDto trainee;

    private TrainerDto trainer;

    private CohortCourseDto cohortCourse;

    private List<FeedbackQuestionCategoryResponseDto> categoryResponses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TraineeDto getTrainee() {
        return trainee;
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

    public List<FeedbackQuestionCategoryResponseDto> getCategoryResponses() {
        return categoryResponses;
    }

    public void setCategoryResponses(List<FeedbackQuestionCategoryResponseDto> categoryResponses) {
        this.categoryResponses = categoryResponses;
    }
}
