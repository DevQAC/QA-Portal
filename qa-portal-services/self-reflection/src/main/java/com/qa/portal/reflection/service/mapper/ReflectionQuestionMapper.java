package com.qa.portal.reflection.service.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;

@Component
public class ReflectionQuestionMapper extends BaseMapper {

	public ReflectionQuestionMapper(DozerBeanMapper mapper) {
		super(mapper);
	}

	public ReflectionQuestionDto mapToReflectionQuestionDto(ReflectionQuestionEntity entity) {
		return this.mapObject(entity, ReflectionQuestionDto.class);
	}

}
