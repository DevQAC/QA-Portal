package com.qa.portal.reflection.service.mapper;

import com.qa.portal.reflection.dto.QuestionDto;
import com.qa.portal.reflection.persistence.entity.QuestionEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;

import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;

@Component
public class ReflectionQuestionMapper {

	private DozerBeanMapper mapper;
	
	private ReflectionRepository reflectionRepository;

	public ReflectionQuestionMapper(DozerBeanMapper mapper, ReflectionRepository reflectionRepository) {
		this.mapper = mapper;
		this.reflectionRepository = reflectionRepository;
	}

	public ReflectionQuestionEntity mapToReflectionQuestionEntity(ReflectionQuestionDto rqdto) {
		ReflectionQuestionEntity rqe = mapper.map(rqdto, ReflectionQuestionEntity.class);
		reflectionRepository.findById(rqdto.getReflectionId()).ifPresent((re) -> rqe.setReflection(re));
		return rqe;
	}

	public ReflectionQuestionDto mapToReflectionQuestionDto(ReflectionQuestionEntity rqe) {
		ReflectionQuestionDto rqdto = mapper.map(rqe, ReflectionQuestionDto.class);
		Optional.ofNullable(rqe.getReflection()).ifPresent((r) -> rqdto.setReflectionId(r.getId()));
		return rqdto;
	}

	public QuestionEntity mapToQuestionEntity(QuestionDto rqdto) {
		return mapper.map(rqdto, QuestionEntity.class);
	}

	public QuestionDto mapToQuestionDto(QuestionEntity rqe) {
		return mapper.map(rqe, QuestionDto.class);
	}

}
