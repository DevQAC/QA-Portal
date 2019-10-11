package com.qa.portal.reflection.service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	private Comparator<ReflectionDto> reflectionByDescendingFormDate = (r1, r2) -> r1.getFormDate().isBefore(r2.getFormDate()) ? 1 : -1;

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
		return traineeRepository.findByUserName(userName)
				.map(t -> getReflectionsByResponder(t))
				.orElseThrow(() -> new QaResourceNotFoundException("No Trainee found for supplied user name"));
	}


	public List<ReflectionDto> getSelfReflectionsForTrainee(Integer traineeId) {
		return traineeRepository.findById(traineeId)
				.map(t -> getReflectionsByResponder(t))
				.orElseThrow(() -> new QaResourceNotFoundException("No Trainee found for supplied id"));
	}

	public Set<ReflectionDto> getSelfReflectionsForTrainer(String userName) {
		TrainerEntity trainer = trainerRepository.findByUserName(userName)
				.orElseThrow(() -> new QaResourceNotFoundException("No Trainer found for supplied user name"));
		return reflectionRepository.findAllByReviewer(trainer)
				.stream().map(reflectionMapper::mapToReflectionDto)
				.collect(Collectors.toSet());
	}

	private List<ReflectionDto> getReflectionsByResponder(TraineeEntity traineeEntity) {
		return reflectionRepository.findAllByResponder(traineeEntity)
				.stream()
				.map(r -> reflectionMapper.mapToReflectionDto(r))
				.sorted(reflectionByDescendingFormDate)
				.collect(Collectors.toList());
	}
}
