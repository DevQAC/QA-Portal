package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCohortCourseEvaluationsForCourseOperation {

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;

    private CohortCourseRepository cohortCourseRepository;

    public GetCohortCourseEvaluationsForCourseOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository,
                                                        CohortCourseEvaluationMapper cohortCourseEvaluationMapper,
                                                        CohortCourseRepository cohortCourseRepository) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
        this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
        this.cohortCourseRepository = cohortCourseRepository;
    }

    public List<CohortCourseEvaluationDto> getEvaluationsForCourse(Integer cohortCourseId) {
        return cohortCourseRepository.findById(cohortCourseId)
                .map(cce -> getEvaluationsForCourse(cce))
                .orElseThrow(() -> new QaPortalBusinessException("Cohort course not found"));
    }

    private List<CohortCourseEvaluationDto> getEvaluationsForCourse(CohortCourseEntity cohortCourseEntity) {
        return this.cohortCourseEvaluationRepository.findByCohortCourse(cohortCourseEntity)
                .stream()
                .map(e -> cohortCourseEvaluationMapper.mapToCohortCourseEvaluationDto(e))
                .collect(Collectors.toList());
    }
}
