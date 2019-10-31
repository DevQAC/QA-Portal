package com.qa.portal.feedback.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.feedback.dto.TraineeEvaluationSummaryDto;
import com.qa.portal.feedback.dto.TraineeEvaluationSummaryRowDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GetTraineeEvaluationSummaryOperation {

    private static final String AWAITING_EVALUATION = "Awaiting Evaluation";

    private static final String NOT_READY_FOR_EVALUATION = "Locked";

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private BaseMapper baseMapper;

    private QaTraineeRepository qaTraineeRepository;

    public GetTraineeEvaluationSummaryOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository,
                                                BaseMapper baseMapper,
                                                QaTraineeRepository qaTraineeRepository) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
        this.baseMapper = baseMapper;
        this.qaTraineeRepository = qaTraineeRepository;
    }

    public TraineeEvaluationSummaryDto getTraineeEvaluationSummary(String traineeUserName) {
        TraineeEvaluationSummaryDto traineeEvaluationSummaryDto = new TraineeEvaluationSummaryDto();
        traineeEvaluationSummaryDto.setEvaluationSummaryRows(
                qaTraineeRepository.findByUserName(traineeUserName)
                        .map(te -> getTraineeEvaluationSummary(te.getCohort(), traineeUserName))
                        .orElseThrow(() -> new QaPortalBusinessException("No trainee found for supplied user name")));
        return traineeEvaluationSummaryDto;
    }

    private List<TraineeEvaluationSummaryRowDto> getTraineeEvaluationSummary(QaCohortEntity qaCohortEntity, String traineeUserName) {
        return Optional.ofNullable(qaCohortEntity).map(ce -> getTraineeEvaluationSummaryRows(ce, traineeUserName))
                .orElseGet(() -> Collections.emptyList());
    }

    private List<TraineeEvaluationSummaryRowDto> getTraineeEvaluationSummaryRows(QaCohortEntity qaCohortEntity, String traineeUserName) {
        return qaCohortEntity.getCohortCourses().stream()
                .map(cce -> createEvaluationSummary(cce, traineeUserName))
                .collect(Collectors.toList());
    }


    private TraineeEvaluationSummaryRowDto createEvaluationSummary(CohortCourseEntity cohortCourseEntity,
                                                                   String traineeUserName) {
        TraineeEvaluationSummaryRowDto traineeEvaluationSummaryRowDto = new TraineeEvaluationSummaryRowDto();
        traineeEvaluationSummaryRowDto.setCohortCourse(baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class));
        traineeEvaluationSummaryRowDto.setEvaluationStatus(getCohortCourseEvaluationStatus(cohortCourseEntity));
        getCohortCourseEvaluationForTrainee(cohortCourseEntity, traineeUserName)
                .ifPresent(ccee -> setTraineeEvaluationSummary(ccee, traineeEvaluationSummaryRowDto));
        return traineeEvaluationSummaryRowDto;
    }

    private Optional<CohortCourseEvaluationEntity> getCohortCourseEvaluationForTrainee(CohortCourseEntity cohortCourseEntity,
                                                                                       String traineeUserName) {
        return cohortCourseEvaluationRepository.findByCohortCourse(cohortCourseEntity).stream()
                .filter(ccee -> ccee.getTrainee().getUserName().equals(traineeUserName))
                .findFirst();
    }

    private void setTraineeEvaluationSummary(CohortCourseEvaluationEntity cohortCourseEvaluationEntity,
                                             TraineeEvaluationSummaryRowDto traineeEvaluationSummaryRowDto) {
        traineeEvaluationSummaryRowDto.setEvaluationId(cohortCourseEvaluationEntity.getId());
        traineeEvaluationSummaryRowDto.setEvaluationStatus(cohortCourseEvaluationEntity.getStatus());
    }

    private String getCohortCourseEvaluationStatus(CohortCourseEntity cohortCourseEntity) {
        if (cohortCourseEntity.getEndDate().toLocalDate().isBefore(LocalDate.now())) {
            return AWAITING_EVALUATION;
        }
        else {
            return NOT_READY_FOR_EVALUATION;
        }
    }
}
