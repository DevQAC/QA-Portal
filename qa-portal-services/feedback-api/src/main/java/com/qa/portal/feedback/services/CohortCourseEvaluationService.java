package com.qa.portal.feedback.services;

import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.dto.TraineeEvaluationSummaryDto;
import com.qa.portal.feedback.dto.TrainerCourseEvaluationSummaryDto;
import com.qa.portal.feedback.dto.TrainerCourseHistoryDto;
import com.qa.portal.feedback.util.QuestionCategorySortUtil;
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

	private CreateCohortCourseEvaluationOperation createCourseEvaluation;

	private QuestionCategorySortUtil questionCategorySortUtil;

	public CohortCourseEvaluationService(GetEvaluationForTraineeAndCourseOperation getEvaluationForTraineeAndCourseOperation,
										 GetTraineeEvaluationSummaryOperation getTraineeEvaluationSummaryOperation,
										 GetCohortCourseEvaluationsForTraineeOperation getCohortCourseEvaluationsForTraineeOperation,
										 GetCohortCourseEvaluationsForCourseOperation getCohortCourseEvaluationsForCourseOperation,
										 GetCohortCoursesForTrainerOperation getCohortCoursesForTrainerOperation,
										 GetCurrentCohortCourseEvaluationForTraineeOperation getCurrentCohortCourseEvaluationForTraineeOperation,
										 GetCohortCourseEvaluationOperation getCohortCourseEvaluationOperation,
										 UpdateCohortCourseEvaluationOperation updateCohortCourseEvaluationOperation,
										 CreateCohortCourseEvaluationOperation createCourseEvaluation,
										 QuestionCategorySortUtil questionCategorySortUtil) {
		this.getEvaluationForTraineeAndCourseOperation = getEvaluationForTraineeAndCourseOperation;
		this.getTraineeEvaluationSummaryOperation = getTraineeEvaluationSummaryOperation;
		this.getCohortCourseEvaluationsForTraineeOperation = getCohortCourseEvaluationsForTraineeOperation;
		this.getCohortCourseEvaluationsForCourseOperation = getCohortCourseEvaluationsForCourseOperation;
		this.getCohortCoursesForTrainerOperation = getCohortCoursesForTrainerOperation;
		this.getCurrentCohortCourseEvaluationForTraineeOperation = getCurrentCohortCourseEvaluationForTraineeOperation;
		this.getCohortCourseEvaluationOperation = getCohortCourseEvaluationOperation;
		this.updateCohortCourseEvaluationOperation = updateCohortCourseEvaluationOperation;
		this.createCourseEvaluation = createCourseEvaluation;
		this.questionCategorySortUtil = questionCategorySortUtil;
	}

	@Transactional
	public CohortCourseEvaluationDto getEvaluationForTraineeAndCourse(String traineeUserName, Integer cohortCourseId) {
		CohortCourseEvaluationDto cohortCourseEvaluationDto =
							getEvaluationForTraineeAndCourseOperation.getEvaluationForTraineeAndCourse(traineeUserName, cohortCourseId);
		questionCategorySortUtil.sortQuestionCategoryResponses(cohortCourseEvaluationDto);
		return cohortCourseEvaluationDto;
	}

	@Transactional
	public TraineeEvaluationSummaryDto getTraineeEvaluationSummary(String traineeUserName) {
		return getTraineeEvaluationSummaryOperation.getTraineeEvaluationSummary(traineeUserName);
	}

	@Transactional
	public List<CohortCourseEvaluationDto> getCohortCourseEvaluationsForTrainee(String traineeUserName) {
		List<CohortCourseEvaluationDto> cohortCourseEvaluationDtos =
									 getCohortCourseEvaluationsForTraineeOperation.getCohortCourseEvaluationsForTrainee(traineeUserName);
		cohortCourseEvaluationDtos.stream()
				.forEach(cce -> questionCategorySortUtil.sortQuestionCategoryResponses(cce));
		return cohortCourseEvaluationDtos;
	}

	@Transactional
	public CohortCourseEvaluationDto getCurrentEvaluationForTrainee(String traineeUserName) {
		CohortCourseEvaluationDto cohortCourseEvaluationDto =
						 getCurrentCohortCourseEvaluationForTraineeOperation.getCohortCourseEvaluation(traineeUserName);
		questionCategorySortUtil.sortQuestionCategoryResponses(cohortCourseEvaluationDto);
		return cohortCourseEvaluationDto;
	}

	@Transactional
	public TrainerCourseHistoryDto getTrainerCourseHistory(String userName) {
		return getCohortCoursesForTrainerOperation.getTrainerCourseHistory(userName);
	}

	@Transactional
	public CohortCourseEvaluationDto getCohortCourseEvaluation(Integer id) {
		CohortCourseEvaluationDto cohortCourseEvaluationDto = getCohortCourseEvaluationOperation.getCohortCourseEvaluation(id);
		questionCategorySortUtil.sortQuestionCategoryResponses(cohortCourseEvaluationDto);
		return cohortCourseEvaluationDto;
	}

	@Transactional
	public TrainerCourseEvaluationSummaryDto getCohortCourseEvaluationsForCourse(Integer cohortCourseId) {
		TrainerCourseEvaluationSummaryDto trainerCourseEvaluationSummaryDto =
									getCohortCourseEvaluationsForCourseOperation.getEvaluationSummaryForCourse(cohortCourseId);
		trainerCourseEvaluationSummaryDto.getTraineeEvaluationsForCourse().stream()
				.forEach(cce -> questionCategorySortUtil.sortQuestionCategoryResponses(cce));
		return trainerCourseEvaluationSummaryDto;
	}

	@Transactional
	public CohortCourseEvaluationDto createCourseEvaluationForTrainee(CohortCourseEvaluationDto courseEvaluation,
																	  String traineeUserName) {
		CohortCourseEvaluationDto cohortCourseEvaluationDto =
										 createCourseEvaluation.createCourseEvaluation(courseEvaluation, traineeUserName);
		questionCategorySortUtil.sortQuestionCategoryResponses(cohortCourseEvaluationDto);
		return cohortCourseEvaluationDto;
	}

	@Transactional
	public CohortCourseEvaluationDto updateCourseEvaluationForTrainee(CohortCourseEvaluationDto courseEvaluation) {
		CohortCourseEvaluationDto cohortCourseEvaluationDto =
										updateCohortCourseEvaluationOperation.updateCourseEvaluation(courseEvaluation);
		questionCategorySortUtil.sortQuestionCategoryResponses(cohortCourseEvaluationDto);
		return cohortCourseEvaluationDto;
	}
}
