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
public class UpdateCohortOperation {

    private final Logger LOGGER = LoggerFactory.getLogger(UpdateCohortOperation.class);

    private QaCohortRepository cohortRepository;

    private CohortMapper cohortMapper;

    public UpdateCohortOperation(QaCohortRepository cohortRepository,
                                 CohortMapper cohortMapper) {
        this.cohortRepository = cohortRepository;
        this.cohortMapper = cohortMapper;
    }

    @Transactional
    public QaCohortDto updateCohort(QaCohortDto cohortDto) {
        QaCohortEntity cohortEntity = cohortMapper.mapToExistingQaCohortEntity(cohortDto);
        QaCohortEntity savedEntity = cohortRepository.save(cohortEntity);
        return cohortMapper.mapToQaCohortDto(savedEntity);
    }
}
