package com.qa.portal.reflection.util.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.reflection.dto.QuestionDto;
import com.qa.portal.reflection.persistence.entity.QuestionEntity;

@Component
public class QuestionMapper extends BaseMapper {

	public QuestionMapper(DozerBeanMapper mapper) {
		super(mapper);
	}

	public QuestionDto mapToQuestionDto(QuestionEntity entity) {
		return this.getMapper().map(entity, QuestionDto.class);
	}

	public QuestionEntity mapToQuestionEntity(QuestionDto dto) {
		return this.getMapper().map(dto, QuestionEntity.class);
	}

}
