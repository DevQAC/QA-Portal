package com.qa.portal.question.services.mapper;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuestionCategoryMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(QuestionCategoryMapper.class);

    private BaseMapper baseMapper;

    public QuestionCategoryMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public QuestionCategoryDto createQuestionCategoryDto(QuestionCategoryEntity questionCategoryEntity) {
        QuestionCategoryDto questionCategoryDto = baseMapper.mapObject(questionCategoryEntity, QuestionCategoryDto.class);
        LOGGER.info("Category is " + questionCategoryDto.getCategoryName());
        questionCategoryDto.getQuestions().stream()
                .forEach(q -> q.setQuestionCategoryName(questionCategoryDto.getCategoryName()));
        return questionCategoryDto;
    }
}
