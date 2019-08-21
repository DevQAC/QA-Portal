package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.CohortCourseDto;

public class TraineeEvaluationSummaryRowDto {

    private CohortCourseDto cohortCourseDto;

    private String evaluationStatus;

    private Integer evaluationId;

    public CohortCourseDto getCohortCourseDto() {
        return cohortCourseDto;
    }

    public void setCohortCourseDto(CohortCourseDto cohortCourseDto) {
        this.cohortCourseDto = cohortCourseDto;
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
