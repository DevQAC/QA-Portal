package com.qa.portal.reflection.service.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.reflection.dto.QuestionDto;
import com.qa.portal.reflection.persistence.entity.QuestionEntity;

@Component
public class QuestionMapper {

	private DozerBeanMapper mapper;

	public QuestionMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	public QuestionDto mapToQuestionDto(QuestionEntity entity) {
		return mapper.map(entity, QuestionDto.class);
	}

	public QuestionEntity mapToQuestionEntity(QuestionDto dto) {
		return mapper.map(dto, QuestionEntity.class);
	}

}
