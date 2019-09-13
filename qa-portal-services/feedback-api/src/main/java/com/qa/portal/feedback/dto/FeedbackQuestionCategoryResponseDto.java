package com.qa.portal.feedback.dto;

import com.qa.portal.common.dto.QuestionCategoryResponseDto;

@Deprecated
public class FeedbackQuestionCategoryResponseDto extends QuestionCategoryResponseDto {

	private CohortCourseFeedbackDto courseFeedback;

    public CohortCourseFeedbackDto getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(CohortCourseFeedbackDto courseFeedback) {
        this.courseFeedback = courseFeedback;
    }
}
