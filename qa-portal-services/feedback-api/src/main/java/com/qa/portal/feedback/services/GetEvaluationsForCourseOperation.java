package com.qa.portal.feedback.services;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetEvaluationsForCourseOperation {

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private BaseMapper baseMapper;

    public GetEvaluationsForCourseOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository,
                                            BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
    }

    public List<CohortCourseEvaluationDto> getEvaluationsForCourse(int cohortCourseId) {
        return this.cohortCourseEvaluationRepository.findAll()
                .stream()
                .map(e -> baseMapper.mapObject(e, CohortCourseEvaluationDto.class))
                .collect(Collectors.toList());
    }
}
