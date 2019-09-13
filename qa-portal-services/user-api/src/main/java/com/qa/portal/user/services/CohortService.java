package com.qa.portal.user.services;

import java.util.List;
import java.util.stream.Collectors;

import com.qa.portal.common.dto.QaCohortDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.util.mapper.BaseMapper;

@Service
public class CohortService {

    private QaCohortRepository repo;

    private BaseMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CohortService.class);

    public CohortService(QaCohortRepository repo, BaseMapper mapper) {
        super();
        this.repo = repo;
        this.mapper = mapper;
    }

    @Transactional
    public List<QaUserDto> getTraineesForCohort(Integer id) {
        QaCohortEntity cohort = this.repo.findById(id)
                .orElseThrow(() -> new QaResourceNotFoundException("Cohort does not exist"));
        return cohort.getTrainees().stream().map(this.mapper::mapToQaUserDto).collect(Collectors.toList());
    }

    @Transactional
    public QaCohortDto getCohortByName(String name) {
        return mapper.mapObject(this.repo.findByName(name)
                .orElseThrow(() -> new QaResourceNotFoundException("Cohort with that name does not exist")), QaCohortDto.class);
    }
}
