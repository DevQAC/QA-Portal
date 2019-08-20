package com.qa.portal.feedback.persistence.entity;

import com.qa.portal.common.persistence.entity.QuestionCategoryResponseEntity;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "FEEDBACK")
public class FeedbackQuestionCategoryResponseEntity extends QuestionCategoryResponseEntity<CohortCourseFeedbackEntity> {

    @ManyToOne
    @JoinColumn(name = "cohort_course_feedback_id")
    private CohortCourseFeedbackEntity courseFeedback;

    public CohortCourseFeedbackEntity getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(CohortCourseFeedbackEntity courseFeedback) {
        this.courseFeedback = courseFeedback;
    }

    @Override
    public CohortCourseFeedbackEntity getParent() {
        return courseFeedback;
    }

    @Override
    public void setParent(CohortCourseFeedbackEntity parent) {
        courseFeedback = parent;
    }
}
