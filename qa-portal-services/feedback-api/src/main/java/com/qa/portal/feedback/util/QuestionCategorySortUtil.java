package com.qa.portal.feedback.util;

import com.qa.portal.common.dto.QuestionCategoryResponseDto;
import com.qa.portal.common.dto.QuestionResponseDto;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class QuestionCategorySortUtil {

    private Comparator<QuestionCategoryResponseDto> questionCategoryResponseDtoComparator
            = Comparator.comparingInt(qcr -> qcr.getQuestionCategory().getId());

    private Comparator<QuestionResponseDto> questionResponseDtoComparator
            = Comparator.comparingInt((qr -> qr.getQuestion().getId()));


    public void sortQuestionCategoryResponses(CohortCourseEvaluationDto cohortCourseEvaluationDto) {
        cohortCourseEvaluationDto.getCategoryResponses().sort(questionCategoryResponseDtoComparator);
        sortQuestionResponses(cohortCourseEvaluationDto.getCategoryResponses());
    }

    public void sortQuestionCategoryResponses(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
        cohortCourseFeedbackDto.getCategoryResponses().sort(questionCategoryResponseDtoComparator);
        sortQuestionResponses(cohortCourseFeedbackDto.getCategoryResponses());
    }

    private void sortQuestionResponses(List<QuestionCategoryResponseDto> questionCategoryResponseDtos) {
        questionCategoryResponseDtos.stream()
                .forEach(qcr -> qcr.getQuestionResponses().sort(questionResponseDtoComparator));
    }
}
