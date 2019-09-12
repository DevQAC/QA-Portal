package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.CohortCourseDto;

import java.util.List;

public class TrainerCourseHistoryDto {

    private CohortCourseDto currentCohortCourse;

    private List<CohortCourseDto> previousCohortCourses;

    private List<CohortCourseDto> cohortCourseHistory;

    private String averageKnowledgeRating;

    private String averageTqiRating;

    public CohortCourseDto getCurrentCohortCourse() {
        return currentCohortCourse;
    }

    public void setCurrentCohortCourse(CohortCourseDto currentCohortCourse) {
        this.currentCohortCourse = currentCohortCourse;
    }

    public List<CohortCourseDto> getPreviousCohortCourses() {
        return previousCohortCourses;
    }

    public void setPreviousCohortCourses(List<CohortCourseDto> previousCohortCourses) {
        this.previousCohortCourses = previousCohortCourses;
    }

    public String getAverageKnowledgeRating() {
        return averageKnowledgeRating;
    }

    public void setAverageKnowledgeRating(String averageKnowledgeRating) {
        this.averageKnowledgeRating = averageKnowledgeRating;
    }

    public String getAverageTqiRating() {
        return averageTqiRating;
    }

    public void setAverageTqiRating(String averageTqiRating) {
        this.averageTqiRating = averageTqiRating;
    }

    public List<CohortCourseDto> getCohortCourseHistory() {
        return cohortCourseHistory;
    }

    public void setCohortCourseHistory(List<CohortCourseDto> cohortCourseHistory) {
        this.cohortCourseHistory = cohortCourseHistory;
    }
}
