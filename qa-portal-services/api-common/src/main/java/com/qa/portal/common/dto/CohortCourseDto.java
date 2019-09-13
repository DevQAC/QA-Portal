package com.qa.portal.common.dto;

import java.time.LocalDate;

public class CohortCourseDto extends QaBaseDto {

    private Integer id;

    private LocalDate startDate;

    private LocalDate endDate;

    private CourseDto course;

    private QaCohortDto cohort;

    private TrainerDto trainer;

    private LocationDto location;

    private String feedbackStatus;

	private String averageKnowledgeRating;
    
    private String tqi;

    private Integer classSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CourseDto getCourse() {
        return course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    public QaCohortDto getCohort() {
        return cohort;
    }

    public void setCohort(QaCohortDto cohort) {
        this.cohort = cohort;
    }

    public TrainerDto getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerDto trainer) {
        this.trainer = trainer;
    }
    
    public String getAverageKnowledgeRating() {
		return averageKnowledgeRating;
	}

	public void setAverageKnowledgeRating(String averageKnowledgeRating) {
		this.averageKnowledgeRating = averageKnowledgeRating;
	}

	public String getTqi() {
		return tqi;
	}

	public void setTqi(String tqi) {
		this.tqi = tqi;
	}

    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public Integer getClassSize() {
        return classSize;
    }

    public void setClassSize(Integer classSize) {
        this.classSize = classSize;
    }
}
