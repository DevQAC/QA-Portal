package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.util.QuestionCategorySortUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CohortCourseFeedbackService {

    private final Logger LOGGER = LoggerFactory.getLogger(CohortCourseFeedbackService.class);

    private GetCohortCourseFeedbackOperation getCohortCourseFeedbackOperation;

    private GetCohortCourseFeedbacksForTrainerOperation getCohortCourseFeedbacksForTrainerOperation;

    private GetCohortCourseFeedbackForCourseOperation getCohortCourseFeedbackForCourseOperation;

    private CreateCohortCourseFeedbackOperation createCohortCourseFeedbackOperation;

    private UpdateCohortCourseFeedbackOperation updateCohortCourseFeedbackOperation;

    private QuestionCategorySortUtil questionCategorySortUtil;

    public CohortCourseFeedbackService(GetCohortCourseFeedbackOperation getCohortCourseFeedbackOperation,
                                       GetCohortCourseFeedbacksForTrainerOperation getCohortCourseFeedbacksForTrainerOperation,
                                       GetCohortCourseFeedbackForCourseOperation getCohortCourseFeedbackForCourseOperation,
                                       CreateCohortCourseFeedbackOperation createCohortCourseFeedbackOperation,
                                       UpdateCohortCourseFeedbackOperation updateCohortCourseFeedbackOperation,
                                       QuestionCategorySortUtil questionCategorySortUtil) {
        this.getCohortCourseFeedbackOperation = getCohortCourseFeedbackOperation;
        this.getCohortCourseFeedbacksForTrainerOperation = getCohortCourseFeedbacksForTrainerOperation;
        this.getCohortCourseFeedbackForCourseOperation = getCohortCourseFeedbackForCourseOperation;
        this.createCohortCourseFeedbackOperation = createCohortCourseFeedbackOperation;
        this.updateCohortCourseFeedbackOperation = updateCohortCourseFeedbackOperation;
        this.questionCategorySortUtil = questionCategorySortUtil;
    }

    @Transactional
    public CohortCourseFeedbackDto getCohortCourseFeedback(Integer cohortCourseFeedbackId) {
        CohortCourseFeedbackDto cohortCourseFeedbackDto =
                                 getCohortCourseFeedbackOperation.getCohortCourseFeedback(cohortCourseFeedbackId);
        questionCategorySortUtil.sortQuestionCategoryResponses(cohortCourseFeedbackDto);
        return cohortCourseFeedbackDto;
    }

    @Transactional
    public List<CohortCourseFeedbackDto> getCohortCourseFeedbacksForTrainer(String trainerUserName) {
        List<CohortCourseFeedbackDto> cohortCourseFeedbackDtos =
                                 getCohortCourseFeedbacksForTrainerOperation.getCohortCourseFeedbacksForTrainer(trainerUserName);
        cohortCourseFeedbackDtos.stream()
                .forEach(ccf -> questionCategorySortUtil.sortQuestionCategoryResponses(ccf));
        return cohortCourseFeedbackDtos;
    }

    @Transactional
    public CohortCourseFeedbackDto getCohortCourseFeedbackForCourse(Integer cohortCourseId) {
        CohortCourseFeedbackDto cohortCourseFeedbackDto =
                                getCohortCourseFeedbackForCourseOperation.getCohortCourseFeedback(cohortCourseId);
        questionCategorySortUtil.sortQuestionCategoryResponses(cohortCourseFeedbackDto);
        return cohortCourseFeedbackDto;
    }

    @Transactional
    public CohortCourseFeedbackDto createCohortCourseFeedback(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
        CohortCourseFeedbackDto dto =
                                 createCohortCourseFeedbackOperation.createCohortCourseFeedback(cohortCourseFeedbackDto);
        questionCategorySortUtil.sortQuestionCategoryResponses(dto);
        return dto;
    }

    @Transactional
    public CohortCourseFeedbackDto updateCohortCourseFeedback(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
        CohortCourseFeedbackDto dto = updateCohortCourseFeedbackOperation.updateCohortCourseFeedback(cohortCourseFeedbackDto);
        questionCategorySortUtil.sortQuestionCategoryResponses(dto);
        return dto;
    }
}
