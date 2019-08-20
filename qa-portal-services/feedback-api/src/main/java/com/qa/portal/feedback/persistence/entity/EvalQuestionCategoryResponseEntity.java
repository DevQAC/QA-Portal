package com.qa.portal.feedback.persistence.entity;

import com.qa.portal.common.persistence.entity.QuestionCategoryResponseEntity;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "EVALUATION")
public class EvalQuestionCategoryResponseEntity extends QuestionCategoryResponseEntity<CohortCourseEvaluationEntity> {

    @ManyToOne
    @JoinColumn(name = "cohort_course_evaluation_id")
    private CohortCourseEvaluationEntity courseEvaluation;

    public CohortCourseEvaluationEntity getCourseEvaluation() {
        return courseEvaluation;
    }

    public void setCourseEvaluation(CohortCourseEvaluationEntity courseEvaluation) {
        this.courseEvaluation = courseEvaluation;
    }

    @Override
    public CohortCourseEvaluationEntity getParent() {
        return courseEvaluation;
    }

    @Override
    public void setParent(CohortCourseEvaluationEntity parent) {
        courseEvaluation = parent;
    }
}
