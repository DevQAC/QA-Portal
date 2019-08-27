package com.qa.portal.feedback.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.dto.TraineeEvaluationSummaryDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CohortCourseEvaluationService {

	private GetEvaluationForTraineeAndCourseOperation getEvaluationForTraineeAndCourseOperation;

	private GetTraineeEvaluationSummaryOperation getTraineeEvaluationSummaryOperation;

	private GetCohortCourseEvaluationsForTraineeOperation getCohortCourseEvaluationsForTraineeOperation;

    private GetCohortCourseEvaluationsForCourseOperation getCohortCourseEvaluationsForCourseOperation;

    private GetCohortCoursesForTrainerOperation getCohortCoursesForTrainerOperation;

	private GetCurrentCohortCourseEvaluationForTraineeOperation getCurrentCohortCourseEvaluationForTraineeOperation;

	private GetCohortCourseEvaluationOperation getCohortCourseEvaluationOperation;

	private UpdateCohortCourseEvaluationOperation updateCohortCourseEvaluationOperation;

	public CreateCohortCourseEvaluationOperation createCourseEvaluation;

	public CohortCourseEvaluationService(GetEvaluationForTraineeAndCourseOperation getEvaluationForTraineeAndCourseOperation,
										 GetTraineeEvaluationSummaryOperation getTraineeEvaluationSummaryOperation,
										 GetCohortCourseEvaluationsForTraineeOperation getCohortCourseEvaluationsForTraineeOperation,
										 GetCohortCourseEvaluationsForCourseOperation getCohortCourseEvaluationsForCourseOperation,
										 GetCohortCoursesForTrainerOperation getCohortCoursesForTrainerOperation,
										 GetCurrentCohortCourseEvaluationForTraineeOperation getCurrentCohortCourseEvaluationForTraineeOperation,
										 GetCohortCourseEvaluationOperation getCohortCourseEvaluationOperation,
										 UpdateCohortCourseEvaluationOperation updateCohortCourseEvaluationOperation,
										 CreateCohortCourseEvaluationOperation createCourseEvaluation) {
		this.getEvaluationForTraineeAndCourseOperation = getEvaluationForTraineeAndCourseOperation;
		this.getTraineeEvaluationSummaryOperation = getTraineeEvaluationSummaryOperation;
		this.getCohortCourseEvaluationsForTraineeOperation = getCohortCourseEvaluationsForTraineeOperation;
		this.getCohortCourseEvaluationsForCourseOperation = getCohortCourseEvaluationsForCourseOperation;
		this.getCohortCoursesForTrainerOperation = getCohortCoursesForTrainerOperation;
		this.getCurrentCohortCourseEvaluationForTraineeOperation = getCurrentCohortCourseEvaluationForTraineeOperation;
		this.getCohortCourseEvaluationOperation = getCohortCourseEvaluationOperation;
		this.updateCohortCourseEvaluationOperation = updateCohortCourseEvaluationOperation;
		this.createCourseEvaluation = createCourseEvaluation;
	}

	@Transactional
	public CohortCourseEvaluationDto getEvaluationForTraineeAndCourse(String traineeUserName, Integer cohortCourseId) {
		return getEvaluationForTraineeAndCourseOperation.getEvaluationForTraineeAndCourse(traineeUserName, cohortCourseId);
	}

	@Transactional
	public TraineeEvaluationSummaryDto getTraineeEvaluationSummary(String traineeUserName) {
		return getTraineeEvaluationSummaryOperation.getTraineeEvaluationSummary(traineeUserName);
	}

	@Transactional
	public List<CohortCourseEvaluationDto> getCohortCourseEvaluationsForTrainee(String traineeUserName) {
		return getCohortCourseEvaluationsForTraineeOperation.getCohortCourseEvaluationsForTrainee(traineeUserName);
	}

	@Transactional
	public CohortCourseEvaluationDto getCurrentEvaluationForTrainee(String traineeUserName) {
		return getCurrentCohortCourseEvaluationForTraineeOperation.getCohortCourseEvaluation(traineeUserName);
	}

	@Transactional
	public List<CohortCourseDto> getCohortCoursesForTrainer(String userName) {
		return getCohortCoursesForTrainerOperation.getCohortCoursesForTrainer(userName);
	}

	@Transactional
	public CohortCourseEvaluationDto getCohortCourseEvaluation(Integer id) {
		return getCohortCourseEvaluationOperation.getCohortCourseEvaluation(id);
	}

	@Transactional
	public List<CohortCourseEvaluationDto> getCohortCourseEvaluationsForCourse(Integer cohortCourseId) {
		return getCohortCourseEvaluationsForCourseOperation.getEvaluationsForCourse(cohortCourseId);
	}

	@Transactional
	public CohortCourseEvaluationDto createCourseEvaluationForTrainee(CohortCourseEvaluationDto courseEvaluation,
																	  String traineeUserName) {
		return createCourseEvaluation.createCourseEvaluation(courseEvaluation, traineeUserName);
	}

	@Transactional
	public CohortCourseEvaluationDto updateCourseEvaluationForTrainee(CohortCourseEvaluationDto courseEvaluation) {
		return updateCohortCourseEvaluationOperation.updateCourseEvaluation(courseEvaluation);
	}
}
