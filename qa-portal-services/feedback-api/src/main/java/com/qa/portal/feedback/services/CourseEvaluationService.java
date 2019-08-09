package com.qa.portal.feedback.services;

import java.util.List;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseEvaluationService {

    private GetEvaluationsForCourseOperation getEvaluationsForCourseOperation;

    public CourseEvaluationService(GetEvaluationsForCourseOperation getEvaluationsForCourseOperation) {
        this.getEvaluationsForCourseOperation = getEvaluationsForCourseOperation;
    }

    @Transactional
    public List<CohortCourseEvaluationDto> getEvaluationsForCourse(String cohortCourseId) {
        return getEvaluationsForCourseOperation.getEvaluationsForCourse(cohortCourseId);
    }
}
