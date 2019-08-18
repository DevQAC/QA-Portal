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

    public CohortCourseEvaluationDto mapToQaCohortCourseEvaluationDto(CohortCourseEvaluationEntity cohortCourseEvaluationEntity) {
        return this.getMapper().map(cohortCourseEvaluationEntity, CohortCourseEvaluationDto.class);
    }
    
    public CohortCourseEvaluationEntity mapToQaCohortCourseEvaluationEntity(CohortCourseEvaluationDto cohortCourseEvaluationDto) {
        return this.getMapper().map(cohortCourseEvaluationDto, CohortCourseEvaluationEntity.class);
    }
}
