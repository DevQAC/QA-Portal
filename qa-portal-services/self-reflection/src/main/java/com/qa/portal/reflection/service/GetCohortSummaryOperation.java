package com.qa.portal.reflection.service;

import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.reflection.dto.CohortSummaryDto;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.repository.ReflectionQuestionRepository;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCohortSummaryOperation {

    private QaCohortRepository cohortRepository;

    private ReflectionRepository reflectionRepository;

    private ReflectionQuestionRepository reflectionQuestionRepository;

    private ReflectionQuestionMapper reflectionQuestionMapper;

    @Autowired
    public GetCohortSummaryOperation(QaCohortRepository cohortRepository,
                                     ReflectionRepository reflectionRepository,
                                     ReflectionQuestionRepository reflectionQuestionRepository,
                                     ReflectionQuestionMapper reflectionQuestionMapper) {
        this.cohortRepository = cohortRepository;
        this.reflectionRepository = reflectionRepository;
        this.reflectionQuestionRepository = reflectionQuestionRepository;
        this.reflectionQuestionMapper = reflectionQuestionMapper;
    }

    public List<CohortSummaryDto> getCohortSummary() {
        return this.cohortRepository.findAll().stream().map(this::buildCSD).collect(Collectors.toList());
    }

    private CohortSummaryDto buildCSD(QaCohortEntity cohort) {
        List<ReflectionQuestionDto> rqes = cohort.getTrainees().stream()
                .flatMap(t -> this.reflectionRepository.findAllByResponder(t).stream())
                .flatMap(r -> this.reflectionQuestionRepository.findAllByReflection(r).stream())
                .map(this.reflectionQuestionMapper::mapToReflectionQuestionDto).collect(Collectors.toList());
        return new CohortSummaryDto(cohort.getName(), rqes);
    }
}
