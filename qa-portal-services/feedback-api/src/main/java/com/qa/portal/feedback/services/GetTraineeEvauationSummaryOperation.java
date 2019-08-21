package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.feedback.dto.TraineeEvaluationSummaryDto;
import com.qa.portal.feedback.dto.TraineeEvaluationSummaryRowDto;
import com.qa.portal.feedback.services.mapper.TraineeEvaluationSummaryMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetTraineeEvauationSummaryOperation {

    private QaTraineeRepository qaTraineeRepository;

    private TraineeEvaluationSummaryMapper traineeEvaluationSummaryMapper;

    public GetTraineeEvauationSummaryOperation(QaTraineeRepository qaTraineeRepository,
                                               TraineeEvaluationSummaryMapper traineeEvaluationSummaryMapper) {
        this.qaTraineeRepository = qaTraineeRepository;
        this.traineeEvaluationSummaryMapper = traineeEvaluationSummaryMapper;
    }

    public TraineeEvaluationSummaryDto getTraineeEvaluationSummary(String traineeUserName) {
        TraineeEvaluationSummaryDto traineeEvaluationSummaryDto = new TraineeEvaluationSummaryDto();
        traineeEvaluationSummaryDto.setEvaluationSummaryRows(
                qaTraineeRepository.findByUserName(traineeUserName)
                        .map(te -> getTraineeEvaluationSummary(te.getCohort(), traineeUserName))
                        .orElseThrow(() -> new QaPortalBusinessException("Error creating trainee evaluation summary")));
        return traineeEvaluationSummaryDto;
    }

    private List<TraineeEvaluationSummaryRowDto> getTraineeEvaluationSummary(QaCohortEntity qaCohortEntity, String traineeUserName) {
        return qaCohortEntity.getCohortCourses().stream()
                .map(cce -> traineeEvaluationSummaryMapper.mapToEvaluationSummary(cce, traineeUserName))
                .collect(Collectors.toList());
    }
}
