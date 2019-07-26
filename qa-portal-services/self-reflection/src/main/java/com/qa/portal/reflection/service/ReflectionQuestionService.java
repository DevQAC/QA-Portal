package com.qa.portal.reflection.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.repository.ReflectionQuestionRepository;
import com.qa.portal.reflection.util.mapper.ReflectionQuestionMapper;

@Service
public class ReflectionQuestionService {
	
	ReflectionQuestionRepository reflectionQuestionRepo;
	
	ReflectionQuestionMapper mapper;
	
	public ReflectionQuestionService(ReflectionQuestionRepository reflectionQuestionRepo, ReflectionQuestionMapper mapper) {
		this.reflectionQuestionRepo = reflectionQuestionRepo;
		this.mapper = mapper;
	}
	
	public Set<ReflectionQuestionDto> getReflectionQuestionsByReflectionId(Integer id) {
		return this.reflectionQuestionRepo.findByReflectionId(id)
				.stream().map(this.mapper::mapToReflectionQuestionDto)
				.collect(Collectors.toUnmodifiableSet());
	}

}
