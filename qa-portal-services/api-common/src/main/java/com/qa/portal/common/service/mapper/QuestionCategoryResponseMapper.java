package com.qa.portal.common.service.mapper;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.dto.QuestionCategoryResponseDto;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class QuestionCategoryResponseMapper<T extends QuestionCategoryResponseDto> {

    private final Logger LOGGER = LoggerFactory.getLogger(QuestionCategoryResponseMapper.class);

    private BaseMapper baseMapper;

    public QuestionCategoryResponseMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public T createQuestionCategoryResponseDto(QuestionCategoryEntity questionCategoryEntity, T responseDto) {
        QuestionCategoryDto questionCategoryDto = baseMapper.mapObject(questionCategoryEntity, QuestionCategoryDto.class);
        responseDto.setQuestionCategory(questionCategoryDto);
        return responseDto;
    }
}
