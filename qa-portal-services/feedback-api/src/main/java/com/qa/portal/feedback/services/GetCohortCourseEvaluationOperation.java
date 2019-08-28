package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
import org.springframework.stereotype.Component;

@Component
public class GetCohortCourseEvaluationOperation {

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;

    public GetCohortCourseEvaluationOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository,
                                              CohortCourseEvaluationMapper cohortCourseEvaluationMapper) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
        this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
    }

    public CohortCourseEvaluationDto getCohortCourseEvaluation(Integer id) {
        return cohortCourseEvaluationRepository.findById(id)
                .map(cce -> cohortCourseEvaluationMapper.mapToCohortCourseEvaluationDto(cce))
                .orElseThrow(() -> new QaPortalBusinessException("No cohort course evaluation found for the supplied id"));
    }
}
