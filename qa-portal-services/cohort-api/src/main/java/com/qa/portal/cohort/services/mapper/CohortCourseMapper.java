package com.qa.portal.cohort.services.mapper;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class CohortCourseMapper {

    private BaseMapper baseMapper;

    public CohortCourseMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public CohortCourseDto mapToCohortCourseDto(CohortCourseEntity cohortCourseEntity) {
        return baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class);
    }
}
