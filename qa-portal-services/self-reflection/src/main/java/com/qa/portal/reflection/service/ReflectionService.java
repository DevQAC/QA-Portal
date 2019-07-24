package com.qa.portal.reflection.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.util.mapper.ReflectionMapper;

@Service
public class ReflectionService {

	private ReflectionRepository reflectionRepo;

	private ReflectionMapper mapper;

	private QaTrainerRepository trainerRepo;

	public ReflectionService(ReflectionRepository repo, ReflectionMapper mapper, QaTrainerRepository trainerRepo) {
		super();
		this.reflectionRepo = repo;
		this.mapper = mapper;
		this.trainerRepo = trainerRepo;
	}

	@Transactional
	public Set<ReflectionDto> getSelfReflectionsForTrainee(String traineeId) {
		return Collections.emptySet();
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
		ReflectionEntity reflection = this.reflectionRepo.findByReviewerAndDate(trainer, date)
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
		reflectionToUpdate.setDate(reflectionToUpdateFrom.getDate());
		reflectionToUpdate.setResponder(reflectionToUpdateFrom.getResponder());
		reflectionToUpdate.setReviewer(reflectionToUpdateFrom.getReviewer());
		return this.mapper.mapToReflectionDto(this.reflectionRepo.save(reflectionToUpdate));
	}

}
