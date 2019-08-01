package com.qa.portal.reflection.service.mapper;

import com.qa.portal.reflection.dto.QuestionDto;
import com.qa.portal.reflection.persistence.entity.QuestionEntity;

import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;

@Component
public class ReflectionQuestionMapper {

	private DozerBeanMapper mapper;

	public ReflectionQuestionMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	public ReflectionQuestionEntity mapToReflectionQuestionEntity(ReflectionQuestionDto rqdto) {
		return mapper.map(rqdto, ReflectionQuestionEntity.class);
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
