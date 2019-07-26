package com.qa.portal.reflection.util.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;

@Component
public class ReflectionQuestionMapper extends BaseMapper {

	public ReflectionQuestionMapper(DozerBeanMapper mapper) {
		super(mapper);
		// TODO Auto-generated constructor stub
	}
	
	public ReflectionQuestionEntity mapToReflectionQuestionEntity(ReflectionQuestionDto rqdto) {
		return this.getMapper().map(rqdto, ReflectionQuestionEntity.class);
	}
	
	public ReflectionQuestionDto mapToReflectionQuestionDto(ReflectionQuestionEntity rqe) {
		return this.getMapper().map(rqe, ReflectionQuestionDto.class);
	}

}
