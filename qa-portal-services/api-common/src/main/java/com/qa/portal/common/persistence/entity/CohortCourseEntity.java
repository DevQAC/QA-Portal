package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(schema = "training", name = "cohort_course")
public class CohortCourseEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "cohort_course_sequence")
    @SequenceGenerator(name = "cohort_course_sequence",
            sequenceName = "training.cohort_course_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cohort_id")
    private QaCohortEntity cohort;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerEntity trainer;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public QaCohortEntity getCohort() {
        return cohort;
    }

    public void setCohort(QaCohortEntity cohort) {
        this.cohort = cohort;
    }

    public TrainerEntity getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CohortCourseEntity{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", course=" + course +
                ", cohort=" + cohort +
                ", trainer=" + trainer +
                ", location=" + location +
                '}';
    }
}
