package com.qa.portal.cohort.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.cohort.services.mapper.CohortCourseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCohortCoursesForTrainerOperation {

    private QaTrainerRepository qaTrainerRepository;

    private CohortCourseMapper cohortCourseMapper;

    public GetCohortCoursesForTrainerOperation(QaTrainerRepository qaTrainerRepository,
                                               CohortCourseMapper cohortCourseMapper) {
        this.qaTrainerRepository = qaTrainerRepository;
        this.cohortCourseMapper = cohortCourseMapper;
    }

    public List<CohortCourseDto> getCohortCoursesForTrainer(String trainerUserName) {
        return qaTrainerRepository.findByUserName(trainerUserName)
                .map(te -> getCohortCourses(te))
                .orElseThrow(() -> new QaPortalBusinessException("No cohort courses for trainer"));
    }

    private List<CohortCourseDto> getCohortCourses(TrainerEntity trainerEntity) {
        return trainerEntity.getCohorts().stream()
                .flatMap(ce -> ce.getCohortCourses().stream())
                .map(cce -> cohortCourseMapper.mapToCohortCourseDto(cce))
                .collect(Collectors.toList());
    }
}
