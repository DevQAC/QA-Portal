package com.qa.portal.feedback.services;

import java.util.List;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qa.portal.common.dto.CohortCourseDto;
;

@Service
public class CourseEvaluationService {

    private GetEvaluationsForCourseOperation getEvaluationsForCourseOperation;

    private GetCoursesEvaluationsForTrainerOperation eval;

	public CreateCourseEvaluationOperation createCourseEvaluation = new CreateCourseEvaluationOperation();

    public CourseEvaluationService(GetEvaluationsForCourseOperation getEvaluationsForCourseOperation) {
        this.getEvaluationsForCourseOperation = getEvaluationsForCourseOperation;
    }

    @Transactional
    public List<CohortCourseEvaluationDto> getEvaluationsForCourse(int cohortCourseId) {
        return getEvaluationsForCourseOperation.getEvaluationsForCourse(cohortCourseId);
    }

	@Transactional
	public List<CohortCourseDto> getCourseEvaluationsForTrainer(String userName) {
		return this.eval.getCourseEvaluationsForTrainer(userName);
	}

	public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		return createCourseEvaluation.createCourseEvaluation(courseEvaluation);
	}
}
