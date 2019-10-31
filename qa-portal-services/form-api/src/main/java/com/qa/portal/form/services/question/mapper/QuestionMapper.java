package com.qa.portal.form.services.question.mapper;

import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    private QuestionCategoryRepository questionCategoryRepository;

    private QuestionRepository questionRepository;

    private BaseMapper baseMapper;

    public QuestionMapper(QuestionCategoryRepository questionCategoryRepository,
                          QuestionRepository questionRepository,
                          BaseMapper baseMapper) {
        this.questionCategoryRepository = questionCategoryRepository;
        this.questionRepository = questionRepository;
        this.baseMapper = baseMapper;
    }

    public QuestionDto mapToQuestionDto(QuestionEntity questionEntity) {
        return baseMapper.mapObject(questionEntity, QuestionDto.class);
    }

    public QuestionEntity mapNewQuestionEntity(QuestionDto questionDto) {
        QuestionEntity questionEntity = baseMapper.mapObject(questionDto, QuestionEntity.class);
        questionCategoryRepository.findByCategoryName(questionDto.getQuestionCategoryName())
                .ifPresent(c -> questionEntity.setCategory(c));
        return questionEntity;
    }

    public QuestionEntity mapUpdatedQuestionEntity(QuestionEntity questionEntity, QuestionDto questionDto) {
        QuestionEntity tempEntity = baseMapper.mapObject(questionDto, QuestionEntity.class);
        questionCategoryRepository.findByCategoryName(questionDto.getQuestionCategoryName())
                .ifPresent(c -> questionEntity.setCategory(c));
        questionEntity.setBody(questionDto.getBody());
        questionEntity.setCommentLabel(questionDto.getCommentLabel());
        questionEntity.setHasComment(questionDto.getHasComment());
        questionEntity.setSelectionOptionsList(tempEntity.getSelectionOptionsList());
        return questionEntity;
    }
}
