package com.qa.portal.common.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CohortCourseDto {

    private Integer id;

    private LocalDate startDate;

    private LocalDate endDate;

    private CourseDto course;

    private QaCohortDto cohort;

    private TrainerDto trainer;
    
	private BigDecimal averageKnowledgeRating;
    
    private BigDecimal tqi;

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
    
    public BigDecimal getAverageKnowledgeRating() {
		return averageKnowledgeRating;
	}

	public void setAverageKnowledgeRating(BigDecimal averageKnowledgeRating) {
		this.averageKnowledgeRating = averageKnowledgeRating;
	}

	public BigDecimal getTqi() {
		return tqi;
	}

	public void setTqi(BigDecimal tqi) {
		this.tqi = tqi;
	}

}
