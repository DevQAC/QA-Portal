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
	private CohortCourseRepository cohortRepo;
	private QaTrainerRepository trainerRepo;
	private Comparator<CohortCourseDto> couComparator = (r1, r2) -> r1.getStartDate().isBefore(r2.getEndDate()) ? 1 : -1; 
	
	@Autowired
	public GetCoursesEvaluationsForTrainerOperation(BaseMapper mapper,
			CohortCourseRepository repo,
			QaTrainerRepository trainerRepo) {
		this.mapper = mapper;
		this.cohortRepo = repo;
		this.trainerRepo = trainerRepo;
	}

	public List<CohortCourseEvaluationDto> getCourseEvaluationsForTrainer(String userName) {
		TrainerEntity trainer = trainerRepo.findByUserName(userName).
				orElseThrow(() -> new QaResourceNotFoundException("Trainer does not exist"));
		return cohortRepo.findByTrainer(trainer)
				.stream()
				.map(c -> mapper.mapObject(c, CohortCourseEvaluationDto.class))
				.sorted(couComparator)
				.collect(Collectors.toList());
	}
	
	private void getTrainerEvaluations(CohortCourseEntity cohortCourseEntity) {
		CohortCourseDto cohortCourseDto = mapper.mapObject(cohortCourseEntity, CohortCourseDto.class);
		cohortCourseEntity.
	}
} 