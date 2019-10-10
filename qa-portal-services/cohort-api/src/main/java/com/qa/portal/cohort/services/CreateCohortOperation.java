package com.qa.portal.cohort.services;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.cohort.services.mapper.CohortMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateCohortOperation {

    private final Logger LOGGER = LoggerFactory.getLogger(CreateCohortOperation.class);

    private QaCohortRepository cohortRepository;

    private CohortMapper cohortMapper;

    public CreateCohortOperation(QaCohortRepository cohortRepository, CohortMapper cohortMapper) {
        this.cohortRepository = cohortRepository;
        this.cohortMapper = cohortMapper;
    }

    @Transactional
    public QaCohortDto createCohort(QaCohortDto qaCohortDto) {
        QaCohortEntity qaCohortEntity = cohortMapper.mapToNewQaCohortEntity(qaCohortDto);
        QaCohortEntity savedEntity =  cohortRepository.save(qaCohortEntity);
        return cohortMapper.mapToQaCohortDto(savedEntity);
    }
}
