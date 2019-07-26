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
import com.qa.portal.reflection.util.mapper.ReflectionMapper;

@Component
public class GetSelfReflectionsForUserOperation {
	
	public Set<ReflectionDto> getSelfReflectionsForUser(String userName, ReflectionRepository reflectionRepo, QaTraineeRepository traineeRepo, ReflectionMapper mapper) {
		TraineeEntity trainee = traineeRepo.findByUserName(userName).stream().findFirst()
				.orElseThrow(() -> new QaResourceNotFoundException("Trainee does not exist"));
		return reflectionRepo.findByResponderId(trainee.getId())
				.stream().map(mapper::mapToReflectionDto)
				.collect(Collectors.toUnmodifiableSet());
	}
	
	public Set<ReflectionDto> getSelfReflectionsForUser(String userName, ReflectionRepository reflectionRepo, QaTrainerRepository trainerRepo, ReflectionMapper mapper) {
		TrainerEntity trainer = trainerRepo.findByUserName(userName).stream().findFirst()
				.orElseThrow(() -> new QaResourceNotFoundException("Trainer does not exist"));
		return reflectionRepo.findByReviewerId(trainer.getId())
				.stream().map(mapper::mapToReflectionDto)
				.collect(Collectors.toUnmodifiableSet());
	}
	
	public Set<ReflectionDto> getSelfReflectionsForUser(Integer traineeId, ReflectionRepository reflectionRepo, QaTraineeRepository traineeRepo, ReflectionMapper mapper) {
		return reflectionRepo.findByResponderId(traineeId)
				.stream().map(mapper::mapToReflectionDto)
				.collect(Collectors.toUnmodifiableSet());
	}
}
