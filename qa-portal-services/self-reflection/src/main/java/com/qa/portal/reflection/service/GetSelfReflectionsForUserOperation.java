package com.qa.portal.reflection.service;

import java.util.Set;
import java.util.stream.Collectors;

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


	public Set<ReflectionDto> getSelfReflectionsForTrainee(String userName) {
		TraineeEntity trainee = traineeRepository.findByUserName(userName).stream().findFirst()
				.orElseThrow(() -> new QaResourceNotFoundException("Trainee does not exist"));
		return reflectionRepository.findByResponderId(trainee.getId())
				.stream().map(reflectionMapper::mapToReflectionDto)
				.collect(Collectors.toSet());
	}
	
	public Set<ReflectionDto> getSelfReflectionsForTrainer(String userName) {
		TrainerEntity trainer = trainerRepository.findByUserName(userName).stream().findFirst()
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
