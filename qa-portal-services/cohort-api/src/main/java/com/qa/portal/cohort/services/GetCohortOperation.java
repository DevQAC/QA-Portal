package com.qa.portal.cohort.services;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.cohort.services.mapper.CohortMapper;
import org.springframework.stereotype.Component;

@Component
public class GetCohortOperation {

    private QaCohortRepository cohortRepository;

    private CohortMapper cohortMapper;

    public GetCohortOperation(QaCohortRepository cohortRepository,
                              CohortMapper cohortMapper) {
        this.cohortRepository = cohortRepository;
        this.cohortMapper = cohortMapper;
    }

    public QaCohortDto getCohort(QaCohortDto cohortDto) {
        return cohortRepository.findById(cohortDto.getId())
                .map(c -> cohortMapper.mapToQaCohortDto(c))
                .orElseThrow(() -> new QaPortalBusinessException("No Cohort found for supplied id"));
    }
}

