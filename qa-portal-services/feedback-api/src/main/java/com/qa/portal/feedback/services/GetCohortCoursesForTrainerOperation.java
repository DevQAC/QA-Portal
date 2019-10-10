package com.qa.portal.feedback.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.feedback.dto.TrainerCourseHistoryDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCohortCoursesForTrainerOperation {

	private final Logger LOGGER = LoggerFactory.getLogger(GetCohortCoursesForTrainerOperation.class);

	private static final Long MAX_COURSES = 8L;

	public static final String NOT_APPLICABLE_STRING = "N/A";

	public static final String AWAITING_FEEDBACK_STATUS = "Awaiting Feedback";

	private BaseMapper mapper;

	private CohortCourseRepository cohortCourseRepo;

	private CohortCourseEvaluationRepository cohortEvaluationRepo;

	private CohortCourseFeedbackRepository cohortCourseFeedbackRepository;

	private EvaluationRatingsCalculator evaluationRatingsCalculator;

	private QaTrainerRepository trainerRepo;

	private Comparator<CohortCourseDto> cohortCourseComparator = (r1, r2) -> r1.getStartDate().isBefore(r2.getEndDate()) ? 1 : -1;

	public GetCohortCoursesForTrainerOperation(BaseMapper mapper,
											   CohortCourseRepository cohortCourseRepo,
											   CohortCourseEvaluationRepository cohortEvaluationRepo,
											   CohortCourseFeedbackRepository cohortCourseFeedbackRepository,
											   EvaluationRatingsCalculator evaluationRatingsCalculator,
											   QaTrainerRepository trainerRepo) {
		this.mapper = mapper;
		this.cohortCourseRepo = cohortCourseRepo;
		this.cohortEvaluationRepo = cohortEvaluationRepo;
		this.cohortCourseFeedbackRepository = cohortCourseFeedbackRepository;
		this.evaluationRatingsCalculator = evaluationRatingsCalculator;
		this.trainerRepo = trainerRepo;
	}

	public TrainerCourseHistoryDto getTrainerCourseHistory(String trainerUserName) {
		TrainerCourseHistoryDto trainerCourseHistoryDto = new TrainerCourseHistoryDto();
		List<CohortCourseDto> cohortCourseDtos = getCohortCoursesForTrainer(trainerUserName);
		trainerCourseHistoryDto.setCurrentCohortCourse(getCurrentCohortCourse(cohortCourseDtos));
		trainerCourseHistoryDto.setCohortCourseHistory(cohortCourseDtos);
		trainerCourseHistoryDto.setPreviousCohortCourses(getPreviousCohortCourses(cohortCourseDtos));
		trainerCourseHistoryDto.setAverageTqiRating(NOT_APPLICABLE_STRING);       // Default value - overwritten below
		trainerCourseHistoryDto.setAverageKnowledgeRating(NOT_APPLICABLE_STRING); // Default value - overwritten below
		evaluationRatingsCalculator.getAverageTqiRatingForCourses(trainerCourseHistoryDto.getPreviousCohortCourses())
				.ifPresent(v -> trainerCourseHistoryDto
						.setAverageTqiRating(evaluationRatingsCalculator.roundToTwoDecimalPlaces(new BigDecimal(v)).toString()));
		evaluationRatingsCalculator.getAverageKnowledgeRatingForCourses(trainerCourseHistoryDto.getPreviousCohortCourses())
				.ifPresent(v -> trainerCourseHistoryDto
						.setAverageKnowledgeRating(evaluationRatingsCalculator.roundToTwoDecimalPlaces(new BigDecimal(v)).toString()));
		return trainerCourseHistoryDto;
	}

	private List<CohortCourseDto> getCohortCoursesForTrainer(String trainerUserName) {
		TrainerEntity trainer = trainerRepo.findByUserName(trainerUserName).
				orElseThrow(() -> new QaResourceNotFoundException("Trainer does not exist"));
		return cohortCourseRepo.findByTrainer(trainer)
				.stream()
				.filter(cce -> !LocalDate.now().isBefore(cce.getStartDate().toLocalDate()))
				.map(c -> getCohortCourseDto(c))
				.sorted(cohortCourseComparator)
				.collect(Collectors.toList());
	}

	private CohortCourseDto getCurrentCohortCourse(List<CohortCourseDto> cohortCourseDtos) {
		return cohortCourseDtos.stream()
				.filter(cc -> isCurrentCourse(cc))
				.findFirst()
				.orElseGet(() -> null);
	}

	private List<CohortCourseDto> getPreviousCohortCourses(List<CohortCourseDto> cohortCourseDtos) {
		return cohortCourseDtos.stream()
				.filter(cc -> !isCurrentCourse(cc))
				.limit(MAX_COURSES)
				.collect(Collectors.toList());
	}

	private boolean isCurrentCourse(CohortCourseDto cohortCourseDto) {
		LocalDate currDate = LocalDate.now();
		return !currDate.isBefore(cohortCourseDto.getStartDate()) && !currDate.isAfter(cohortCourseDto.getEndDate());
	}
	
	private CohortCourseDto getCohortCourseDto(CohortCourseEntity cohortCourseEntity) {
		CohortCourseDto cohortCourseDto = mapper.mapObject(cohortCourseEntity, CohortCourseDto.class);
		cohortCourseDto.setFeedbackStatus(AWAITING_FEEDBACK_STATUS);
		cohortCourseDto.setAverageKnowledgeRating(NOT_APPLICABLE_STRING);
		cohortCourseDto.setTqi(NOT_APPLICABLE_STRING);
		cohortCourseFeedbackRepository.findByCohortCourse(cohortCourseEntity)
				.ifPresent(ccfe -> cohortCourseDto.setFeedbackStatus(ccfe.getStatus()));
		List<CohortCourseEvaluationEntity> evaluations = cohortEvaluationRepo.findByCohortCourse(cohortCourseEntity);
		evaluationRatingsCalculator.getAverageKnowledgeRatingForCourse(evaluations)
				.ifPresent(e -> cohortCourseDto.setAverageKnowledgeRating(
						evaluationRatingsCalculator.roundToTwoDecimalPlaces(new BigDecimal(e)).toString()));
		evaluationRatingsCalculator.getTqiRatingForCourse(evaluations)
				.ifPresent(e ->
						cohortCourseDto.setTqi(evaluationRatingsCalculator.roundToTwoDecimalPlaces(new BigDecimal(e)).toString()));
		cohortCourseDto.setClassSize(getClassSize(cohortCourseEntity));
		return cohortCourseDto;
	}

	private Integer getClassSize(CohortCourseEntity cohortCourseEntity) {
		return cohortCourseEntity.getCohort().getTrainees().size();
	}
}
