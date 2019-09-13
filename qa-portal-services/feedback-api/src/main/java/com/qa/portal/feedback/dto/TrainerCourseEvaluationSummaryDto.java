package com.qa.portal.feedback.dto;

import java.util.List;

public class TrainerCourseEvaluationSummaryDto {

    private List<CohortCourseEvaluationDto> traineeEvaluationsForCourse;

    private String courseTqi;

    private String courseHistoryTqi;

    public List<CohortCourseEvaluationDto> getTraineeEvaluationsForCourse() {
        return traineeEvaluationsForCourse;
    }

    public void setTraineeEvaluationsForCourse(List<CohortCourseEvaluationDto> traineeEvaluationsForCourse) {
        this.traineeEvaluationsForCourse = traineeEvaluationsForCourse;
    }

    public String getCourseTqi() {
        return courseTqi;
    }

    public void setCourseTqi(String courseTqi) {
        this.courseTqi = courseTqi;
    }

    public String getCourseHistoryTqi() {
        return courseHistoryTqi;
    }

    public void setCourseHistoryTqi(String courseHistoryTqi) {
        this.courseHistoryTqi = courseHistoryTqi;
    }
}
