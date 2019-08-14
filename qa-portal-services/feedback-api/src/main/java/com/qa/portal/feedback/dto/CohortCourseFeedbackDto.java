package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.TrainerDto;

import java.util.List;

public class CohortCourseFeedbackDto {
    private Integer id;

    private CohortCourseDto cohortCourse;

    private List<FeedbackQuestionCategoryResponseDto> categoryResponses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
