package com.qa.portal.feedback.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.persistence.entity.EvalQuestionCategoryResponseEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Component
public class GetCohortCoursesForTrainerOperation {

	private final Logger LOGGER = LoggerFactory.getLogger(GetCohortCoursesForTrainerOperation.class);

	private static final String TRAINER_EVALUATION = "Evaluation Trainer";

	private BaseMapper mapper;

	private CohortCourseRepository cohortCourseRepo;

	private CohortCourseEvaluationRepository cohortEvaluationRepo;

	private CohortCourseFeedbackRepository cohortCourseFeedbackRepository;

	private QaTrainerRepository trainerRepo;

	private Comparator<CohortCourseDto> cohortCourseComparator = (r1, r2) -> r1.getStartDate().isBefore(r2.getEndDate()) ? 1 : -1;

	public GetCohortCoursesForTrainerOperation(BaseMapper mapper,
											   CohortCourseRepository cohortCourseRepo,
											   CohortCourseEvaluationRepository cohortEvaluationRepo,
											   CohortCourseFeedbackRepository cohortCourseFeedbackRepository,
											   QaTrainerRepository trainerRepo) {
		this.mapper = mapper;
		this.cohortCourseRepo = cohortCourseRepo;
		this.cohortEvaluationRepo = cohortEvaluationRepo;
		this.cohortCourseFeedbackRepository = cohortCourseFeedbackRepository;
		this.trainerRepo = trainerRepo;
	}

	public List<CohortCourseDto> getCohortCoursesForTrainer(String userName) {
		TrainerEntity trainer = trainerRepo.findByUserName(userName).
				orElseThrow(() -> new QaResourceNotFoundException("Trainer does not exist"));
		return cohortCourseRepo.findByTrainer(trainer)
				.stream()
				.map(c -> getCohortCourseDto(c))
				.sorted(cohortCourseComparator)
				.collect(Collectors.toList());
	}
	
	private CohortCourseDto getCohortCourseDto(CohortCourseEntity cohortCourseEntity) {
		CohortCourseDto cohortCourseDto = mapper.mapObject(cohortCourseEntity, CohortCourseDto.class);
		cohortCourseDto.setFeedbackStatus("Awaiting Feedback");
		cohortCourseFeedbackRepository.findByCohortCourse(cohortCourseEntity)
				.ifPresent(ccfe -> cohortCourseDto.setFeedbackStatus(ccfe.getStatus()));
		OptionalDouble evaluation = cohortEvaluationRepo.findByCohortCourse(cohortCourseEntity)
								.stream()
								.flatMap(e -> e.getCategoryResponses().stream())
								.filter(cr -> cr.getQuestionCategory().getCategoryName().equals(TRAINER_EVALUATION))
								.map(cr -> getEvaluationResponseValue(cr))
								.filter(s -> !s.contains("N/A"))
								.mapToInt(s -> Integer.valueOf(s))
								.average();
		cohortCourseDto.setAverageKnowledgeRating("N/A");
		evaluation.ifPresent(e -> cohortCourseDto.setAverageKnowledgeRating(new BigDecimal(e).toString()));
		return cohortCourseDto;
	}

	private String getEvaluationResponseValue(EvalQuestionCategoryResponseEntity questionCategoryResponseEntity) {
		return questionCategoryResponseEntity.getQuestionResponses()
				.stream()
				.findFirst()
				.map(qr -> convertResponseValueToString(qr.getResponseValues()))
				.orElseThrow(()  -> new QaPortalBusinessException("Error calculating Trainer evaluation"));
	}

	private String convertResponseValueToString(String responseValues) {
		try {
			LOGGER.info("Response Values " + responseValues);
			ObjectMapper om = new ObjectMapper();
			TypeFactory typeFactory = om.getTypeFactory();
			List<String> values = om.readValue(responseValues, typeFactory.constructCollectionType(List.class, String.class));
			if (values.size() > 0) {
				return values.get(0);
			}
			return "N/A";
		}
		catch(Exception e) {
			throw new QaPortalBusinessException("Error calculating Trainer evaluation");
		}
	}
} 





























