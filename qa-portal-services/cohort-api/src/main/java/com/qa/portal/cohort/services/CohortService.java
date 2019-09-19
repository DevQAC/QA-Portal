package com.qa.portal.cohort.services;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.util.mapper.CohortMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CohortService {

    private final Logger LOGGER = LoggerFactory.getLogger(CohortService.class);

    private QaCohortRepository cohortRepository;

    private CohortMapper cohortMapper;

    private Comparator<QaCohortDto> cohortDtoComparator = (c1, c2) -> c1.getStartDate().isBefore(c2.getStartDate()) ? -1 : 1;

    public CohortService(QaCohortRepository cohortRepository,
                         CohortMapper cohortMapper) {
        this.cohortRepository = cohortRepository;
        this.cohortMapper = cohortMapper;
    }

    public List<QaCohortDto> getAllCohorts() {
        return cohortRepository.findAll().stream()
                .map(ce -> cohortMapper.mapToQaCohortDto(ce))
                .sorted(cohortDtoComparator.reversed())
                .collect(Collectors.toList());
    }
}
