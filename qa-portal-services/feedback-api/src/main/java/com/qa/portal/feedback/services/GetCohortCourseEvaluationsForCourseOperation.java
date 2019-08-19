package com.qa.portal.feedback.services;

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

    public GetCohortCourseEvaluationsForCourseOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository, CohortCourseEvaluationMapper cohortCourseEvaluationMapper) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
        this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
    }

    public List<CohortCourseEvaluationDto> getEvaluationsForCourse(Integer cohortCourseId) {
        return this.cohortCourseEvaluationRepository.findAll()
                .stream()
                .map(e -> cohortCourseEvaluationMapper.mapToCohortCourseEvaluationDto(e))
                .collect(Collectors.toList());
    }
}
