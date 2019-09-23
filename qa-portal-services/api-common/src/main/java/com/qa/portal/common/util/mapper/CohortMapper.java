package com.qa.portal.common.util.mapper;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import org.springframework.stereotype.Component;

@Component
public class CohortMapper {

    private QaCohortRepository cohortRepository;

    private QaTrainerRepository trainerRepository;

    private BaseMapper baseMapper;

    public CohortMapper(QaCohortRepository cohortRepository, QaTrainerRepository trainerRepository, BaseMapper baseMapper) {
        this.cohortRepository = cohortRepository;
        this.trainerRepository = trainerRepository;
        this.baseMapper = baseMapper;
    }

    public QaCohortDto mapToQaCohortDto(QaCohortEntity qaCohortEntity) {
        QaCohortDto cohortDto = baseMapper.mapObject(qaCohortEntity, QaCohortDto.class);
        cohortDto.setTrainerUserName(qaCohortEntity.getTrainer().getUserName());
        return cohortDto;
    }

    public QaCohortEntity mapToQaCohortEntity(QaCohortDto qaCohortDto) {
        QaCohortEntity qaCohortEntity = baseMapper.mapObject(qaCohortDto, QaCohortEntity.class);
        trainerRepository.findByUserName(qaCohortDto.getTrainerUserName())
                .ifPresent(t -> qaCohortEntity.setTrainer(t));
        return qaCohortEntity;
    }
}
