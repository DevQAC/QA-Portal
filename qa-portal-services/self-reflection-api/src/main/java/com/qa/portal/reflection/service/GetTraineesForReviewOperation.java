package com.qa.portal.reflection.service;

import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetTraineesForReviewOperation {

    private QaCohortRepository cohortRepository;

    private ReflectionRepository reflectionRepository;

    private BaseMapper baseMapper;

    @Autowired
    public GetTraineesForReviewOperation(QaCohortRepository cohortRepository, ReflectionRepository reflectionRepository, BaseMapper baseMapper) {
        this.cohortRepository = cohortRepository;
        this.reflectionRepository = reflectionRepository;
        this.baseMapper = baseMapper;
    }

    public List<QaUserDto> getTraineesToReviewForCohort(Integer cohortId) {
        QaCohortEntity cohort = this.cohortRepository.findById(cohortId)
                .orElseThrow(() -> new QaResourceNotFoundException("No Cohort found for supplied id"));
        return cohort.getTrainees()
                .stream()
                .filter(this::hasReflectionForReview)
                .map(this.baseMapper::mapToQaUserDto)
                .collect(Collectors.toList());
    }

    private boolean hasReflectionForReview(TraineeEntity traineeEntity) {
        return reflectionRepository.findAllByResponder(traineeEntity)
                .stream()
                .filter(r -> r.getStatus().equals("Submitted"))
                .findAny()
                .map(r -> true)
                .orElseGet(() -> false);
    }
}
