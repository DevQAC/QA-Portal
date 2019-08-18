package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
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

    public CohortCourseFeedbackService(GetCohortCourseFeedbackOperation getCohortCourseFeedbackOperation,
                                       GetCohortCourseFeedbacksForTrainerOperation getCohortCourseFeedbacksForTrainerOperation,
                                       GetCohortCourseFeedbackForCourseOperation getCohortCourseFeedbackForCourseOperation,
                                       CreateCohortCourseFeedbackOperation createCohortCourseFeedbackOperation,
                                       UpdateCohortCourseFeedbackOperation updateCohortCourseFeedbackOperation) {
        this.getCohortCourseFeedbackOperation = getCohortCourseFeedbackOperation;
        this.getCohortCourseFeedbacksForTrainerOperation = getCohortCourseFeedbacksForTrainerOperation;
        this.getCohortCourseFeedbackForCourseOperation = getCohortCourseFeedbackForCourseOperation;
        this.createCohortCourseFeedbackOperation = createCohortCourseFeedbackOperation;
        this.updateCohortCourseFeedbackOperation = updateCohortCourseFeedbackOperation;
    }

    @Transactional
    public CohortCourseFeedbackDto getCohortCourseFeedback(Integer cohortCourseFeedbackId) {
        return getCohortCourseFeedbackOperation.getCohortCourseFeedback(cohortCourseFeedbackId);
    }

    @Transactional
    public List<CohortCourseFeedbackDto> getCohortCourseFeedbacksForTrainer(String trainerUserName) {
        return getCohortCourseFeedbacksForTrainerOperation.getCohortCourseFeedbacksForTrainer(trainerUserName);
    }

    @Transactional
    public CohortCourseFeedbackDto getCohortCourseFeedbackForCourse(Integer cohortCourseId) {
        return getCohortCourseFeedbackForCourseOperation.getCohortCourseFeedback(cohortCourseId);
    }

    @Transactional
    public CohortCourseFeedbackDto createCohortCourseFeedback(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
        return createCohortCourseFeedbackOperation.createFeedbackForm(cohortCourseFeedbackDto);
    }

    @Transactional
    public CohortCourseFeedbackDto updateCohortCourseFeedback(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
        return updateCohortCourseFeedbackOperation.updateCohortCourseFeedback(cohortCourseFeedbackDto);
    }
}
