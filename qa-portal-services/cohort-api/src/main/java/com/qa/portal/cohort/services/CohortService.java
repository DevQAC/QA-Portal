package com.qa.portal.cohort.services;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.cohort.services.mapper.CohortMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CohortService {

    private final Logger LOGGER = LoggerFactory.getLogger(CohortService.class);

    private QaCohortRepository cohortRepository;

    private CohortMapper cohortMapper;

    private BaseMapper mapper;

    private Comparator<QaCohortDto> cohortDtoComparator = (c1, c2) -> c1.getStartDate().isBefore(c2.getStartDate()) ? -1 : 1;

    public CohortService(QaCohortRepository cohortRepository,
                         CohortMapper cohortMapper,
                         BaseMapper mapper) {
        this.cohortRepository = cohortRepository;
        this.cohortMapper = cohortMapper;
        this.mapper = mapper;
    }

    @Transactional
    public QaCohortDto getCohortForId(Integer cohortId) {
        return cohortRepository.findById(cohortId)
                .map(e -> cohortMapper.mapToQaCohortDto(e))
                .orElseThrow(() -> new QaPortalBusinessException("No cohort found for supplied id"));
    }

    @Transactional
    public List<QaCohortDto> getAllCohorts() {
        return cohortRepository.findAll().stream()
                .map(ce -> cohortMapper.mapToQaCohortDto(ce))
                .sorted(cohortDtoComparator.reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<QaUserDto> getTraineesForCohort(Integer id) {
        QaCohortEntity cohort = cohortRepository.findById(id)
                .orElseThrow(() -> new QaResourceNotFoundException("Cohort does not exist"));
        return cohort.getTrainees().stream()
                .map(this.mapper::mapToQaUserDto).collect(Collectors.toList());
    }

    @Transactional
    public QaCohortDto getCohortByName(String name) {
        return mapper.mapObject(cohortRepository.findByName(name)
                .orElseThrow(() -> new QaResourceNotFoundException("Cohort with that name does not exist")), QaCohortDto.class);
    }
}
