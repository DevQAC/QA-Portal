package com.qa.portal.feedback.persistence.entity;

import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QaBaseEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "training", name = "cohort_course_evaluation")
public class CohortCourseEvaluationEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "cohort_course_evaluation_sequence")
    @SequenceGenerator(name = "cohort_course_evaluation_sequence",
            sequenceName = "training.cohort_course_evaluation_sequence",
            allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "trainee_id")
    private TraineeEntity trainee;

    @ManyToOne
    @JoinColumn(name = "cohort_course_id")
    private CohortCourseEntity cohortCourse;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "courseEvaluation", cascade = CascadeType.ALL)
    private List<EvalQuestionCategoryResponseEntity> categoryResponses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TraineeEntity getTrainee() {
        return trainee;
    }

    public void setTrainee(TraineeEntity trainee) {
        this.trainee = trainee;
    }

    public List<EvalQuestionCategoryResponseEntity> getCategoryResponses() {
        return categoryResponses;
    }

    public void setCategoryResponses(List<EvalQuestionCategoryResponseEntity> categoryResponses) {
        this.categoryResponses = categoryResponses;
    }

    public CohortCourseEntity getCohortCourse() {
        return cohortCourse;
    }

    public void setCohortCourse(CohortCourseEntity cohortCourse) {
        this.cohortCourse = cohortCourse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
