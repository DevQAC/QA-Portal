package com.qa.portal.feedback.services;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;

public class GetCoursesEvaluationsForTrainerOperation {

	private BaseMapper mapper;
	private CohortCourseRepository cohortCourseRepo;
	private CohortCourseEvaluationRepository cohortEvaluationRepo;
	private QaTrainerRepository trainerRepo;
	private Comparator<CohortCourseDto> cohortCourseComparator = (r1, r2) -> r1.getStartDate().isBefore(r2.getEndDate()) ? 1 : -1;
	private static final String TRAINER_EVALUATION = "Evaluation Trainer";

	
	@Autowired
	public GetCoursesEvaluationsForTrainerOperation(BaseMapper mapper,
			CohortCourseRepository repo,
			QaTrainerRepository trainerRepo,
			CohortCourseEvaluationRepository cohortEvaluationRepo) {
		this.mapper = mapper;
		this.cohortCourseRepo = repo;
		this.cohortEvaluationRepo = cohortEvaluationRepo;
		this.trainerRepo = trainerRepo;
	}

	public List<CohortCourseDto> getCourseEvaluationsForTrainer(String userName) {
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
		Double evaluation = cohortEvaluationRepo.findByCohortCourse(cohortCourseEntity)
								.stream()
								.flatMap(e -> e.getCategoryResponses().stream())
								.filter(cr -> cr.getQuestionCategory().getCategoryName().equals(TRAINER_EVALUATION))
								.mapToInt(cr -> getEvaluationResponseValue(cr))
								.average()
								.orElseThrow(() -> new QaPortalBusinessException("Error calculating Trainer evaluation"));
		cohortCourseDto.setAverageKnowledgeRating(new BigDecimal(evaluation));
		return cohortCourseDto;
	}

	private Integer getEvaluationResponseValue(QuestionCategoryResponseEntity questionCategoryResponseEntity) {
		return questionCategoryResponseEntity.getQuestionResponses()
				.stream()
				.findFirst()
				.map(qr -> convertResponseValueToInt(qr.getResponseValues()))
				.orElseThrow(()  -> new QaPortalBusinessException("Error calculating Trainer evaluation"));
	}

	private Integer convertResponseValueToInt(String responseValues) {
		try {
			ObjectMapper om = new ObjectMapper();
			TypeFactory typeFactory = om.getTypeFactory();
			List<Integer> values = om.readValue(responseValues, typeFactory.constructCollectionType(List.class, Integer.class));
			return values.get(0);
		}
		catch(Exception e) {
			throw new QaPortalBusinessException("Error calculating Trainer evaluation");
		}
	}
} 





























