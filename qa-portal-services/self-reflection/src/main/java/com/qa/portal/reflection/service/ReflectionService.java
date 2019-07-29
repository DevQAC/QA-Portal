package com.qa.portal.reflection.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.reflection.dto.CohortSummaryDto;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionQuestionRepository;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionQuestionMapper;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;

@Service
public class ReflectionService {

	private ReflectionRepository reflectionRepo;

	private ReflectionMapper mapper;

	private QaTrainerRepository trainerRepo;

	private QaTraineeRepository traineeRepo;

	private GetSelfReflectionsForUserOperation getSelfReflectionsForUserOperation;

	private QaCohortRepository cohortRepo;

	private ReflectionQuestionMapper rqMapper;

	private ReflectionQuestionRepository reflectionQuestionRepository;

	public ReflectionService(ReflectionRepository reflectionRepo,
                             ReflectionMapper mapper,
                             QaTrainerRepository trainerRepo,
                             QaTraineeRepository traineeRepo,
                             GetSelfReflectionsForUserOperation getSelfReflectionsForUserOperation,
                             QaCohortRepository cohortRepo,
                             ReflectionQuestionMapper rqMapper,
                             ReflectionQuestionRepository reflectionQuestionRepository) {
		super();
		this.reflectionRepo = reflectionRepo;
		this.mapper = mapper;
		this.trainerRepo = trainerRepo;
		this.getSelfReflectionsForUserOperation = getSelfReflectionsForUserOperation;
		this.traineeRepo = traineeRepo;
        this.cohortRepo = cohortRepo;
        this.rqMapper = rqMapper;
        this.reflectionQuestionRepository = reflectionQuestionRepository;

    }

	@Transactional
	public Set<ReflectionDto> getSelfReflectionsForTrainee(String userName) {
		return this.getSelfReflectionsForUserOperation.getSelfReflectionsForUser(userName, this.reflectionRepo, this.traineeRepo, this.mapper);
	}

    @Transactional
    public Set<ReflectionDto> getSelfReflectionsForTrainer(String userName) {
        return this.getSelfReflectionsForUserOperation.getSelfReflectionsForUser(userName, this.reflectionRepo, this.trainerRepo, this.mapper);
    }

    @Transactional
    public Set<ReflectionDto> getSelfReflectionsForTrainee(Integer traineeId) {
        return this.getSelfReflectionsForUserOperation.getSelfReflectionsForUser(traineeId, this.reflectionRepo, this.traineeRepo, this.mapper);
    }

    @Transactional
    public ReflectionDto getSelfReflection(Integer id) {
        ReflectionEntity reflection = this.reflectionRepo.findById(id)
                .orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
        return this.mapper.mapToReflectionDto(reflection);
    }

    @Transactional
    public ReflectionDto getSelfReflection(Integer userId, LocalDate date) {
        TrainerEntity trainer = this.trainerRepo.findById(userId)
                .orElseThrow(() -> new QaResourceNotFoundException("Trainer does not exist"));
        ReflectionEntity reflection = this.reflectionRepo.findByReviewerAndFormDate(trainer, date)
                .orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
        return this.mapper.mapToReflectionDto(reflection);
    }

    @Transactional
    public ReflectionDto createSelfReflection(ReflectionDto reflection, String userName) {
        return this.mapper.mapToReflectionDto(this.reflectionRepo.save(this.mapper.mapToReflectionEntity(reflection)));
    }


    @Transactional
    public ReflectionDto updateSelfReflection(ReflectionDto reflection) {
        ReflectionEntity reflectionToUpdate = this.reflectionRepo.findById(reflection.getId())
                .orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
        ReflectionEntity reflectionToUpdateFrom = this.mapper.mapToReflectionEntity(reflection);
        reflectionToUpdate.setFormDate(reflectionToUpdateFrom.getFormDate());
        reflectionToUpdate.setResponder(reflectionToUpdateFrom.getResponder());
        reflectionToUpdate.setReviewer(reflectionToUpdateFrom.getReviewer());
        return this.mapper.mapToReflectionDto(this.reflectionRepo.save(reflectionToUpdate));
    }

    @Transactional
    public List<CohortSummaryDto> getCohortSummaryDto() {
	    return this.cohortRepo.findAll().stream().map(this::buildCSD).collect(Collectors.toList());
    }


    private CohortSummaryDto buildCSD(QaCohortEntity cohort) {
        List<ReflectionQuestionDto> rqes = cohort.getTrainees().stream()
                .flatMap(t -> this.reflectionRepo.findAllByResponder(t).stream())
                .flatMap(r -> this.reflectionQuestionRepository.findAllByReflection(r).stream())
                .map(this.rqMapper::mapToReflectionQuestionDto).collect(Collectors.toList());
        return new CohortSummaryDto(cohort.getName(), rqes);
    }
}
