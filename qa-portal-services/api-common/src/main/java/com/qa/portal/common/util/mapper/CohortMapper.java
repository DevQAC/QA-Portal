package com.qa.portal.common.util.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.persistence.entity.QaCohortEntity;

@Component
public class CohortMapper extends BaseMapper {
	
	public CohortMapper(DozerBeanMapper mapper) {
		super(mapper);
	}

	public QaCohortDto mapToQaCohortDto(QaCohortEntity qaCohortEntity) {
		return this.getMapper().map(qaCohortEntity, QaCohortDto.class);
	}
}
