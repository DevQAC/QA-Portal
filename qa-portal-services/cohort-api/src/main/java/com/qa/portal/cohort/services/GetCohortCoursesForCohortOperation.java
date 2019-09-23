package com.qa.portal.cohort.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.cohort.services.mapper.CohortCourseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCohortCoursesForCohortOperation {

    private QaCohortRepository qaCohortRepository;

    private CohortCourseMapper cohortCourseMapper;

    public GetCohortCoursesForCohortOperation(QaCohortRepository qaCohortRepository,
                                              CohortCourseMapper cohortCourseMapper) {
        this.qaCohortRepository = qaCohortRepository;
        this.cohortCourseMapper = cohortCourseMapper;
    }

    public List<CohortCourseDto> getCohortCoursesForCohort(Integer cohortId) {
        return qaCohortRepository.findById(cohortId)
                .map(ce -> getCohortCourses(ce))
                .orElseThrow(() -> new QaPortalBusinessException("Cannot find Cohort for supplied id"));
    }

    private List<CohortCourseDto> getCohortCourses(QaCohortEntity qaCohortEntity) {
        return qaCohortEntity.getCohortCourses().stream()
                .map(cce -> cohortCourseMapper.mapToCohortCourseDto(cce))
                .collect(Collectors.toList());
    }
}
