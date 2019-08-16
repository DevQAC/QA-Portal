package com.qa.portal.feedback.services.mapper;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class CohortCourseEvaluationMapper extends BaseMapper {

    public CohortCourseEvaluationMapper(DozerBeanMapper mapper) {
        super(mapper);
    }

    public CohortCourseEvaluationDto mapToQaCohortCourseEvaluationDto(CohortCourseEvaluationEntity qaCohortEntity) {
        return this.getMapper().map(qaCohortEntity, CohortCourseEvaluationDto.class);
    }
    
    public CohortCourseEvaluationEntity mapToQaCohortCourseEvaluationEntity(CohortCourseEvaluationDto qaCohortDto) {
        return this.getMapper().map(qaCohortDto, CohortCourseEvaluationEntity.class);
    }
}
