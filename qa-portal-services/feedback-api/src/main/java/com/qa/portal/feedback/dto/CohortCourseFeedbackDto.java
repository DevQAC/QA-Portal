package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.QuestionCategoryResponseDto;

import java.util.List;

public class CohortCourseFeedbackDto {

    private Integer id;

    private CohortCourseDto cohortCourse;

    private List<QuestionCategoryResponseDto> categoryResponses;

    private String status;

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

    @Override
    public String toString() {
        return "CohortCourseFeedbackDto{" +
                "id=" + id +
                ", cohortCourse=" + cohortCourse +
                ", categoryResponses=" + categoryResponses +
                ", status='" + status + '\'' +
                '}';
    }
}
