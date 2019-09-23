package com.qa.portal.cohort.services;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.util.mapper.CohortMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateCohortOperation {

    private QaCohortRepository cohortRepository;

    private CohortMapper cohortMapper;

    public CreateCohortOperation(QaCohortRepository cohortRepository, CohortMapper cohortMapper) {
        this.cohortRepository = cohortRepository;
        this.cohortMapper = cohortMapper;
    }

    public QaCohortDto createCohort(QaCohortDto qaCohortDto) {
        QaCohortEntity qaCohortEntity = cohortMapper.mapToQaCohortEntity(qaCohortDto);
        qaCohortEntity = cohortRepository.save(qaCohortEntity);
        return cohortMapper.mapToQaCohortDto(qaCohortEntity);
    }
}
