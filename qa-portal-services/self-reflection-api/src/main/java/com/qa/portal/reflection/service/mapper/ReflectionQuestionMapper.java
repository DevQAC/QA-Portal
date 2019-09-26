package com.qa.portal.reflection.service.mapper;

import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
		rqdto.setQuestion(mapToQuestionDto(rqe.getQuestion()));
		return rqdto;
	}

	public QuestionDto mapToQuestionDto(QuestionEntity rqe) {
		QuestionDto questionDto = mapper.map(rqe, QuestionDto.class);
		questionDto.setQuestionCategoryName(rqe.getCategory().getCategoryName());
		return questionDto;
	}
}
