package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseFeedbackMapper;
import org.springframework.stereotype.Component;

@Component
public class UpdateCohortCourseFeedbackOperation {

    private CohortCourseFeedbackRepository cohortCourseFeedbackRepository;

    private CohortCourseFeedbackMapper cohortCourseFeedbackMapper;


    public CohortCourseFeedbackDto updateCohortCourseFeedback(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
        return cohortCourseFeedbackDto;
    }
}
