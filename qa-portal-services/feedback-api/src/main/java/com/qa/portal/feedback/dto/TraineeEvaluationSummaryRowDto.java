package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.CohortCourseDto;

public class TraineeEvaluationSummaryRowDto {

    private CohortCourseDto cohortCourse;

    private String evaluationStatus;

    private Integer evaluationId;

    public CohortCourseDto getCohortCourse() {
        return cohortCourse;
    }

    public void setCohortCourse(CohortCourseDto cohortCourse) {
        this.cohortCourse = cohortCourse;
    }

    public String getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(String evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }
}
