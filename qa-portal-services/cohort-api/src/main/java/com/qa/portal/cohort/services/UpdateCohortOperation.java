package com.qa.portal.cohort.services;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.util.mapper.CohortMapper;
import org.springframework.stereotype.Component;

@Component
public class UpdateCohortOperation {

    private QaCohortRepository cohortRepository;

    private CohortMapper cohortMapper;

    public UpdateCohortOperation(QaCohortRepository cohortRepository,
                                 CohortMapper cohortMapper) {
        this.cohortRepository = cohortRepository;
        this.cohortMapper = cohortMapper;
    }

    public QaCohortDto updateCohort(QaCohortDto cohortDto) {
        QaCohortEntity cohortEntity = cohortMapper.mapToQaCohortEntity(cohortDto);
        cohortEntity = cohortRepository.save(cohortEntity);
        return cohortMapper.mapToQaCohortDto(cohortEntity);
    }
}
