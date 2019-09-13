package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.QuestionCategoryResponseDto;

@Deprecated
public class EvalQuestionCategoryResponseDto extends QuestionCategoryResponseDto {

    private CohortCourseEvaluationDto courseEvaluation;

    public CohortCourseEvaluationDto getCourseEvaluation() {
        return courseEvaluation;
    }

    public void setCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
        this.courseEvaluation = courseEvaluation;
    }
}
