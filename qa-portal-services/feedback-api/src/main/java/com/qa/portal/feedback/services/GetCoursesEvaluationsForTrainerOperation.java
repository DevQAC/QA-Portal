package com.qa.portal.feedback.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;

public class GetCoursesEvaluationsForTrainerOperation {

	private BaseMapper mapper;
	private CohortCourseRepository cohortCourseRepo;
	private CohortCourseEvaluationRepository cohortEvaluationRepo;
	private QaTrainerRepository trainerRepo;
	private Comparator<CohortCourseDto> couComparator = (r1, r2) -> r1.getStartDate().isBefore(r2.getEndDate()) ? 1 : -1;
	private static final String TRAINER_CATEGORY = "Evaluation Trainer";
	
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
				.map(c -> getEvaluatedCohortCourseDto(c))
				.sorted(couComparator)
				.collect(Collectors.toList());
	}
	
	private CohortCourseDto getEvaluatedCohortCourseDto(CohortCourseEntity cohortCourseEntity) {
		CohortCourseDto cohortCourseDto = mapper.mapObject(cohortCourseEntity, CohortCourseDto.class);
		return cohortCourseDto;
	}
	
	private Double calculateKnowledgeAverage(CohortCourseEntity cohortCourseEntity) {
		cohortEvaluationRepo.findByCohortCourse(cohortCourseEntity).stream()
		.map(e -> e.getCategoryResponses().stream()
		.filter(r -> r.getQuestionCategory().getCategoryName().equals(TRAINER_CATEGORY)));
		return null;
	}
	
	private Boolean isTrainerResponse() {
		
	}
} 

	



























