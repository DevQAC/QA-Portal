package com.qa.portal.user.services;

import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.util.mapper.CohortMapper;
import org.springframework.stereotype.Component;

@Component
public class GetTraineeOperation {

    private QaTraineeRepository traineeRepository;

    private CohortMapper cohortMapper;

    public GetTraineeOperation(QaTraineeRepository traineeRepository,
                               CohortMapper cohortMapper) {
        this.traineeRepository = traineeRepository;
        this.cohortMapper = cohortMapper;
    }

    public TraineeDto getTraineeById(Integer id) {
        return this.cohortMapper.mapToQaTraineeDto(traineeRepository.findById(id)
                .orElseThrow(() -> new QaResourceNotFoundException("Trainee not found")));
    }
}
