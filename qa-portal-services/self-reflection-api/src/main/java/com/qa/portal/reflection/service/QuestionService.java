package com.qa.portal.reflection.service;

import java.util.Set;
import java.util.stream.Collectors;

import com.qa.portal.common.dto.QuestionDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.persistence.entity.CohortQuestionEntity;
import com.qa.portal.common.persistence.repository.CohortQuestionRepository;
import com.qa.portal.reflection.service.mapper.QuestionMapper;

@Service
public class QuestionService {

	private CohortQuestionRepository cohortQuestionRepo;

	private QaCohortRepository cohortRepo;

	private QuestionMapper mapper;

	public QuestionService(CohortQuestionRepository cohortQuestionRepo, QaCohortRepository cohortRepo,
			QuestionMapper mapper) {
		super();
		this.cohortQuestionRepo = cohortQuestionRepo;
		this.cohortRepo = cohortRepo;
		this.mapper = mapper;
	}

	@Transactional
	public Set<QuestionDto> getQuestionsForCohort(Integer cohortId) {
		QaCohortEntity cohort = this.cohortRepo.findById(cohortId)
				.orElseThrow(() -> new QaResourceNotFoundException("No Cohort found for supplied id"));
		Set<CohortQuestionEntity> cohortQuestions = this.cohortQuestionRepo.findByCohort(cohort);
		return cohortQuestions.stream().map(cq -> cq.getQuestion()).map(this.mapper::mapToQuestionDto)
				.collect(Collectors.toSet());
	}

}
