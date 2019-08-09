package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class GetEvaluationsForCourseOperation {

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    public GetEvaluationsForCourseOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
    }

    public List<CohortCourseEvaluationDto> getEvaluationsForCourse(String cohortCourseId) {
        // All DB access, mapping from Entity to DTO return the response to the service
        return Collections.emptyList();
    }

}
