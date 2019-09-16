package com.qa.portal.reflection.service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;

@Component
public class GetSelfReflectionsForUserOperation {

	private ReflectionMapper reflectionMapper;

	private ReflectionRepository reflectionRepository;

	private QaTraineeRepository traineeRepository;

	private QaTrainerRepository trainerRepository;

	private Comparator<ReflectionDto> reflectionComparator = (r1, r2) -> r1.getFormDate().isBefore(r2.getFormDate()) ? 1 : -1;

	@Autowired
	public GetSelfReflectionsForUserOperation(ReflectionMapper reflectionMapper,
											  ReflectionRepository reflectionRepository,
											  QaTraineeRepository traineeRepository,
											  QaTrainerRepository trainerRepository) {
		this.reflectionMapper = reflectionMapper;
		this.reflectionRepository = reflectionRepository;
		this.traineeRepository = traineeRepository;
		this.trainerRepository = trainerRepository;
	}

	public List<ReflectionDto> getSelfReflectionsForTrainee(String userName) {
		TraineeEntity trainee = traineeRepository.findByUserName(userName)
				.orElseThrow(() -> new QaResourceNotFoundException("Trainee does not exist"));
		return reflectionRepository.findByResponderId(trainee.getId())
				.stream()
				.map(reflectionMapper::mapToReflectionDto)
				.sorted(reflectionComparator)
				.collect(Collectors.toList());
	}
	
	public Set<ReflectionDto> getSelfReflectionsForTrainer(String userName) {
		TrainerEntity trainer = trainerRepository.findByUserName(userName)
				.orElseThrow(() -> new QaResourceNotFoundException("Trainer does not exist"));
		return reflectionRepository.findByReviewerId(trainer.getId())
				.stream().map(reflectionMapper::mapToReflectionDto)
				.collect(Collectors.toSet());
	}
	
	public Set<ReflectionDto> getSelfReflectionsForUser(Integer traineeId) {
		return reflectionRepository.findByResponderId(traineeId)
				.stream().map(reflectionMapper::mapToReflectionDto)
				.collect(Collectors.toSet());
	}
}
