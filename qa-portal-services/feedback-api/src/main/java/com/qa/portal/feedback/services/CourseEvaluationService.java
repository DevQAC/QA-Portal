package com.qa.portal.feedback.services;

import java.util.List;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qa.portal.common.dto.CohortCourseDto;

@Service
public class CourseEvaluationService {

    private GetEvaluationsForCourseOperation getEvaluationsForCourseOperation;

    private GetCoursesEvaluationsForTrainerOperation eval;

	private UpdateCourseEvaluationOperation update;

	public CreateCourseEvaluationOperation createCourseEvaluation;



    @Transactional
    public List<CohortCourseEvaluationDto> getEvaluationsForCourse(int cohortCourseId) {
        return getEvaluationsForCourseOperation.getEvaluationsForCourse(cohortCourseId);
    }

	@Transactional
	public List<CohortCourseDto> getCourseEvaluationsForTrainer(String userName) {
		return this.eval.getCourseEvaluationsForTrainer(userName);
	}

	@Transactional
	public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		return createCourseEvaluation.createCourseEvaluation(courseEvaluation);
	}

	@Transactional
	public CohortCourseEvaluationDto updateCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		return update.updateCourseEvaluation(courseEvaluation);
	}
}
