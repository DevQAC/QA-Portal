package com.qa.portal.feedback.persistence.entity;

import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QaBaseEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "training", name = "cohort_course_feedback")
public class CohortCourseFeedbackEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "cohort_course_feedback_sequence")
    @SequenceGenerator(name = "cohort_course_feedback_sequence",
            sequenceName = "training.cohort_course_feedback_sequence",
            allocationSize = 1)
    private Integer id;

    @OneToMany(mappedBy = "courseFeedback")
    private List<FeedbackQuestionCategoryResponseEntity> categoryResponses;

    @ManyToOne
    @JoinColumn(name = "cohort_course_id")
    private CohortCourseEntity cohortCourse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<FeedbackQuestionCategoryResponseEntity> getCategoryResponses() {
        return categoryResponses;
    }

    public void setCategoryResponses(List<FeedbackQuestionCategoryResponseEntity> categoryResponses) {
        this.categoryResponses = categoryResponses;
    }

    public CohortCourseEntity getCohortCourse() {
        return cohortCourse;
    }

    public void setCohortCourse(CohortCourseEntity cohortCourse) {
        this.cohortCourse = cohortCourse;
    }
}
