package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CohortCourseFeedbackService {

    private final Logger LOGGER = LoggerFactory.getLogger(CohortCourseFeedbackService.class);

    private CreateCohortCourseFeedbackOperation feedbackOp;

    private GetCohortCourseFeedbackOperation getCohortCourseFeedbackOperation;

    public CohortCourseFeedbackService(CreateCohortCourseFeedbackOperation feedbackOp, GetCohortCourseFeedbackOperation getCohortCourseFeedbackOperation) {
        this.feedbackOp = feedbackOp;
        this.getCohortCourseFeedbackOperation = getCohortCourseFeedbackOperation;
    }

    @Transactional
    public CohortCourseFeedbackDto createCourseFeedbackForCourse(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
        return this.feedbackOp.createFeedbackForm(cohortCourseFeedbackDto);
    }

    public CohortCourseFeedbackDto getCohortCourseFeedbackDto(Integer cohortCourseId) {
        return getCohortCourseFeedbackOperation.getCohortCourseFeedback(cohortCourseId);
    }
}
