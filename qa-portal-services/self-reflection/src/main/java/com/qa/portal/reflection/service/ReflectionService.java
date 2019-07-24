package com.qa.portal.reflection.service;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.util.mapper.ReflectionMapper;

@Service
public class ReflectionService {

	private ReflectionRepository repo;

	private ReflectionMapper mapper;

	public ReflectionService(ReflectionRepository repo, ReflectionMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	public Set<ReflectionDto> getSelfReflectionsForTrainee(Integer traineeId) {
		return null;
	}

	public ReflectionDto getSelfReflection(Integer id) {
		ReflectionEntity reflection = this.repo.findById(id)
				.orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
		return this.mapper.mapToReflectionDto(reflection);
	}

	public ReflectionDto getSelfReflection(Integer userId, LocalDate date) {
		ReflectionEntity reflection = this.repo.findByReviewerAndDate(userId, date)
				.orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
		return this.mapper.mapToReflectionDto(reflection);
	}

	public ReflectionDto createSelfReflection(ReflectionDto reflection) {
		return this.mapper.mapToReflectionDto(this.repo.save(this.mapper.mapToReflectionEntity(reflection)));
	}

	public ReflectionDto updateSelfReflection(ReflectionDto reflection, Integer id) {
		ReflectionEntity reflectionToUpdate = this.repo.findById(id)
				.orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
		ReflectionEntity reflectionToUpdateFrom = this.mapper.mapToReflectionEntity(reflection);
		reflectionToUpdate.setDate(reflectionToUpdateFrom.getDate());
		reflectionToUpdate.setResponder(reflectionToUpdateFrom.getResponder());
		reflectionToUpdate.setReviewer(reflectionToUpdateFrom.getReviewer());
		return this.mapper.mapToReflectionDto(this.repo.save(reflectionToUpdate));
	}

}
