package com.qa.portal.feedback.services;

import java.util.List;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qa.portal.common.dto.CohortCourseDto;

@Service
public class CourseEvaluationService {

    private GetEvaluationsForCourseOperation getEvaluationsForCourseOperation;

    private GetCoursesEvaluationsForTrainerOperation getCoursesEvaluationsForTrainerOperation;

	private UpdateCourseEvaluationOperation updateCourseEvaluationOperation;

	public CreateCourseEvaluationOperation createCourseEvaluation;

	public CourseEvaluationService(GetEvaluationsForCourseOperation getEvaluationsForCourseOperation,
								   GetCoursesEvaluationsForTrainerOperation getCoursesEvaluationsForTrainerOperation,
								   UpdateCourseEvaluationOperation updateCourseEvaluationOperation,
								   CreateCourseEvaluationOperation createCourseEvaluation) {
		this.getEvaluationsForCourseOperation = getEvaluationsForCourseOperation;
		this.getCoursesEvaluationsForTrainerOperation = getCoursesEvaluationsForTrainerOperation;
		this.updateCourseEvaluationOperation = updateCourseEvaluationOperation;
		this.createCourseEvaluation = createCourseEvaluation;
	}

	@Transactional
    public List<CohortCourseEvaluationDto> getEvaluationsForCourse(int cohortCourseId) {
        return getEvaluationsForCourseOperation.getEvaluationsForCourse(cohortCourseId);
    }

	@Transactional
	public List<CohortCourseDto> getCourseEvaluationsForTrainer(String userName) {
		return this.getCoursesEvaluationsForTrainerOperation.getCourseEvaluationsForTrainer(userName);
	}

	@Transactional
	public CohortCourseEvaluationDto createCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		return createCourseEvaluation.createCourseEvaluation(courseEvaluation);
	}

	@Transactional
	public CohortCourseEvaluationDto updateCourseEvaluation(CohortCourseEvaluationDto courseEvaluation) {
		return updateCourseEvaluationOperation.updateCourseEvaluation(courseEvaluation);
	}
}
