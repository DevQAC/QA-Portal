package com.qa.portal.reflection.service.mapper;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;

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

	public QuestionEntity mapToQuestionEntity(QuestionDto rqdto) {
		return mapper.map(rqdto, QuestionEntity.class);
	}

	public QuestionDto mapToQuestionDto(QuestionEntity rqe) {
		QuestionDto questionDto = mapper.map(rqe, QuestionDto.class);
//		setOptionsListForQuestion(questionDto);
		questionDto.setQuestionCategoryName(rqe.getCategory().getCategoryName());
		return questionDto;
	}

//	private void setOptionsListForQuestion(QuestionDto question) {
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			TypeFactory typeFactory = objectMapper.getTypeFactory();
//			question.setSelectionOptionsList(objectMapper.readValue(question.getSelectionOptionsJson(), typeFactory.constructCollectionType(List.class, String.class)));
//		}
//		catch (Exception e) {
//			throw new QaPortalBusinessException("Could not get options for form questions.");
//		}
//	}
}
