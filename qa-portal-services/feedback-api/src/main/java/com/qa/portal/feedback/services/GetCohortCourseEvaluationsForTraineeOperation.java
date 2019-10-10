package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCohortCourseEvaluationsForTraineeOperation {

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private QaTraineeRepository traineeRepository;

    private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;

    private BaseMapper baseMapper;

    public GetCohortCourseEvaluationsForTraineeOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository,
                                                         QaTraineeRepository traineeRepository,
                                                         CohortCourseEvaluationMapper cohortCourseEvaluationMapper,
                                                         BaseMapper baseMapper) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
        this.traineeRepository = traineeRepository;
        this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
        this.baseMapper = baseMapper;
    }

    public List<CohortCourseEvaluationDto> getCohortCourseEvaluationsForTrainee(String traineeUserName) {
        TraineeEntity traineeEntity = getTrainee(traineeUserName);
        return cohortCourseEvaluationRepository.findByTrainee(traineeEntity)
                .stream()
                .map(ccef -> cohortCourseEvaluationMapper.mapToCohortCourseEvaluationDto(ccef))
                .collect(Collectors.toList());
    }

    private TraineeEntity getTrainee(String traineeUserName) {
        return traineeRepository.findByUserName(traineeUserName)
                .orElseThrow(() -> new QaPortalBusinessException("No trainee for supplied user name"));
    }
}
