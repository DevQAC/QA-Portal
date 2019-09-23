package com.qa.portal.user.services;

import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.common.util.mapper.CohortMapper;
import org.springframework.stereotype.Component;

@Component
public class GetTraineeOperation {

    private QaTraineeRepository traineeRepository;

    private BaseMapper baseMapper;

    public GetTraineeOperation(QaTraineeRepository traineeRepository,
                               BaseMapper baseMapper) {
        this.traineeRepository = traineeRepository;
        this.baseMapper = baseMapper;
    }

    public TraineeDto getTraineeById(Integer id) {
        return traineeRepository.findById(id)
                .map((entity) -> baseMapper.mapObject(entity, TraineeDto.class))
                .orElseThrow(() -> new QaResourceNotFoundException("Trainee not found"));
    }
}
