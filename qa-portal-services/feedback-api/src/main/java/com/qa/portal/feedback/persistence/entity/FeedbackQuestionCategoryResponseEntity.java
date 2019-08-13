package com.qa.portal.feedback.persistence.entity;

import com.qa.portal.common.persistence.entity.QuestionCategoryResponseEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "training", name = "question_category_response")
@DiscriminatorValue(value = "FEEDBACK")
public class FeedbackQuestionCategoryResponseEntity extends QuestionCategoryResponseEntity {

    @ManyToOne
    @JoinColumn(name = "cohort_course_feedback_id")
    private CohortCourseFeedbackEntity courseFeedback;

    public CohortCourseFeedbackEntity getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(CohortCourseFeedbackEntity courseFeedback) {
        this.courseFeedback = courseFeedback;
    }
}
