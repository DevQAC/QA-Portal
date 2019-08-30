package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseFeedbackMapper;
import org.springframework.stereotype.Component;

@Component
public class GetCohortCourseFeedbackOperation {

    private CohortCourseFeedbackRepository cohortCourseFeedbackRepository;

    private CohortCourseFeedbackMapper cohortCourseFeedbackMapper;

    public GetCohortCourseFeedbackOperation(CohortCourseFeedbackRepository cohortCourseFeedbackRepository,
                                            CohortCourseFeedbackMapper cohortCourseFeedbackMapper) {
        this.cohortCourseFeedbackRepository = cohortCourseFeedbackRepository;
        this.cohortCourseFeedbackMapper = cohortCourseFeedbackMapper;
    }

    public CohortCourseFeedbackDto getCohortCourseFeedback(Integer cohortCourseFeedbackId) {
        return cohortCourseFeedbackRepository.findById(cohortCourseFeedbackId)
                .map(ccfe -> cohortCourseFeedbackMapper.mapToCohortCourseFeedbackDto(ccfe))
                .orElseThrow(() -> new QaPortalBusinessException("Cannot find Cohort Course feedback"));
    }
}
