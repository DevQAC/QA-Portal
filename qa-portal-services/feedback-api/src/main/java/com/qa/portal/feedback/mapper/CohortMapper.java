package com.qa.portal.feedback.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;

@Component
public class CohortMapper extends BaseMapper {

    public CohortMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public CohortCourseEvaluationDto mapToQaCohortDto(CohortCourseEvaluationEntity qaCohortEntity) {
        return this.getMapper().map(qaCohortEntity, CohortCourseEvaluationDto.class);
    }
    
    public CohortCourseEvaluationEntity mapToQaCohortEntity(CohortCourseEvaluationDto qaCohortDto) {
        return this.getMapper().map(qaCohortDto, CohortCourseEvaluationEntity.class);
    }
}
