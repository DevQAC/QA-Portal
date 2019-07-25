package com.qa.portal.reflection.util.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;

@Component
public class ReflectionMapper extends BaseMapper {

	public ReflectionMapper(DozerBeanMapper mapper) {
		super(mapper);
	}

	public ReflectionDto mapToReflectionDto(ReflectionEntity entity) {
		return this.getMapper().map(entity, ReflectionDto.class);
	}

	public ReflectionEntity mapToReflectionEntity(ReflectionDto dto) {
		return this.getMapper().map(dto, ReflectionEntity.class);
	}

}
