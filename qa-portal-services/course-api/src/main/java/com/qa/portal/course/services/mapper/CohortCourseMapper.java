package com.qa.portal.course.services.mapper;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class CohortCourseMapper {

    private BaseMapper baseMapper;

    public CohortCourseMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public QuestionCategoryDto createQuestionCategoryDto(QuestionCategoryEntity questionCategoryEntity) {
        return baseMapper.mapObject(questionCategoryEntity, QuestionCategoryDto.class);
    }
}
