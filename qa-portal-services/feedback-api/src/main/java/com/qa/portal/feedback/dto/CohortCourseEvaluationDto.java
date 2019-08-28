package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.QuestionCategoryResponseDto;
import com.qa.portal.common.dto.TraineeDto;

import java.util.List;

public class CohortCourseEvaluationDto {
    private Integer id;

    private TraineeDto trainee;

    private CohortCourseDto cohortCourse;

    private String status;

    private List<QuestionCategoryResponseDto> categoryResponses;

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

    public CohortCourseDto getCohortCourse() {
        return cohortCourse;
    }

    public void setCohortCourse(CohortCourseDto cohortCourse) {
        this.cohortCourse = cohortCourse;
    }

    public List<QuestionCategoryResponseDto> getCategoryResponses() {
        return categoryResponses;
    }

    public void setCategoryResponses(List<QuestionCategoryResponseDto> categoryResponses) {
        this.categoryResponses = categoryResponses;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
