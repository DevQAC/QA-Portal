package com.qa.portal.cohort.services.user;

import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
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
                .orElseThrow(() -> new QaResourceNotFoundException("No trainee found for supplied id"));
    }
}
