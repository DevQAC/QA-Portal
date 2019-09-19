package com.qa.portal.cohort.services;

import com.qa.portal.common.dto.CohortCourseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CohortCourseService {

    private GetCohortCoursesForTrainerOperation getCohortCoursesForTrainerOperation;

    private GetCohortCoursesForCohortOperation getCohortCoursesForCohortOperation;

    public CohortCourseService(GetCohortCoursesForTrainerOperation getCohortCoursesForTrainerOperation,
                               GetCohortCoursesForCohortOperation getCohortCoursesForCohortOperation) {
        this.getCohortCoursesForTrainerOperation = getCohortCoursesForTrainerOperation;
        this.getCohortCoursesForCohortOperation = getCohortCoursesForCohortOperation;
    }

    @Transactional
    public List<CohortCourseDto> getCohortCoursesForTrainer(String trainerUserName) {
        return getCohortCoursesForTrainerOperation.getCohortCoursesForTrainer(trainerUserName);
    }

    @Transactional
    public List<CohortCourseDto> getCohortCoursesForCohort(Integer cohortId) {
        return getCohortCoursesForCohortOperation.getCohortCoursesForCohort(cohortId);
    }
}
