package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(schema = "training", name = "qa_cohort")
public class QaCohortEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "qa_cohort_sequence")
    @SequenceGenerator(name = "qa_cohort_sequence",
            sequenceName = "training.qa_cohort_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "cohort_name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @OneToMany(mappedBy = "cohort",
            fetch = FetchType.LAZY)
    private Set<TraineeEntity> trainees;

    @OneToMany(mappedBy = "cohort",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CohortCourseEntity> cohortCourses;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerEntity trainer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TraineeEntity> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<TraineeEntity> trainees) {
        this.trainees = trainees;
    }

    public void addTrainee(TraineeEntity trainee) {
        if (this.trainees == null) {
            this.trainees = new HashSet<>();
        }
        this.trainees.add(trainee);
        trainee.setCohort(this);
    }

    public void removeTrainee(TraineeEntity trainee) {
        this.trainees.remove(trainee);
        trainee.setCohort(null);
    }

    public TrainerEntity getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    public List<CohortCourseEntity> getCohortCourses() {
        return cohortCourses;
    }

    public void setCohortCourses(List<CohortCourseEntity> cohortCourses) {
        this.cohortCourses = cohortCourses;
    }

    public void addCohortCourse(CohortCourseEntity cohortCourse) {
        if (this.cohortCourses == null) {
            this.cohortCourses = new ArrayList<>();
        }
        this.cohortCourses.add(cohortCourse);
        cohortCourse.setCohort(this);
    }

    public void removeCohortCourse(CohortCourseEntity cohortCourseEntity) {
        this.cohortCourses.remove(cohortCourseEntity);
        cohortCourseEntity.setCohort(null);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
