package com.qa.portal.reflection.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionQuestionRepository;
import com.qa.portal.reflection.util.mapper.ReflectionQuestionMapper;

@Service
public class ReflectionQuestionService {
	
	ReflectionQuestionRepository reflectionQuestionRepo;
	
	ReflectionQuestionMapper mapper;

	private QaSecurityContext context;
	
	public ReflectionQuestionService(ReflectionQuestionRepository reflectionQuestionRepo, ReflectionQuestionMapper mapper, QaSecurityContext context) {
		this.reflectionQuestionRepo = reflectionQuestionRepo;
		this.mapper = mapper;
		this.context = context;
	}
	
	@Transactional
	public Set<ReflectionQuestionDto> getReflectionQuestionsByReflectionId(Integer id) {
		return this.reflectionQuestionRepo.findByReflectionId(id)
				.stream().map(this.mapper::mapToReflectionQuestionDto)
				.collect(Collectors.toUnmodifiableSet());
	}
	
	@Transactional
	public Set<ReflectionQuestionDto> updateReflectionQuestions(Set<ReflectionQuestionDto> reflectionQuestions) {
		return reflectionQuestions.stream()
		.map(rqdto -> {
			ReflectionQuestionEntity reflectionQuestionToUpdate = this.reflectionQuestionRepo.findById(rqdto.getId())
					.orElseThrow(() -> new QaResourceNotFoundException("Reflection Question not found"));
			ReflectionQuestionEntity reflectionQuestionToUpdateFrom = this.mapper.mapToReflectionQuestionEntity(rqdto);
			reflectionQuestionToUpdate.setResponse(reflectionQuestionToUpdateFrom.getResponse());
			reflectionQuestionToUpdate.setTrainerResponse(reflectionQuestionToUpdateFrom.getTrainerResponse());
			reflectionQuestionToUpdate.setLastUpdatedBy(context.getUserName());
			return this.mapper.mapToReflectionQuestionDto(this.reflectionQuestionRepo.save(reflectionQuestionToUpdate));
		})
		.collect(Collectors.toUnmodifiableSet());
	}

}
