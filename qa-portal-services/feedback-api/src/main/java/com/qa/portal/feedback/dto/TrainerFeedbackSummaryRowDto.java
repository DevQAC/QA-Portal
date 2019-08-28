package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.CohortCourseDto;

public class TrainerFeedbackSummaryRowDto {

    private CohortCourseDto cohortCourse;

    private String feedbackStatus;

    private Integer feedbackFormId;

    public CohortCourseDto getCohortCourse() {
        return cohortCourse;
    }

    public void setCohortCourse(CohortCourseDto cohortCourse) {
        this.cohortCourse = cohortCourse;
    }

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public Integer getFeedbackFormId() {
        return feedbackFormId;
    }

    public void setFeedbackFormId(Integer feedbackFormId) {
        this.feedbackFormId = feedbackFormId;
    }
}
