package com.qa.portal.form.services.category.mapper;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class QuestionCategoryMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(QuestionCategoryMapper.class);

    private QuestionCategoryRepository questionCategoryRepository;

    private QuestionRepository questionRepository;

    private BaseMapper baseMapper;

    public QuestionCategoryMapper(QuestionCategoryRepository questionCategoryRepository,
                                  QuestionRepository questionRepository,
                                  BaseMapper baseMapper) {
        this.questionCategoryRepository = questionCategoryRepository;
        this.questionRepository = questionRepository;
        this.baseMapper = baseMapper;
    }

    public QuestionCategoryDto createQuestionCategoryDto(QuestionCategoryEntity questionCategoryEntity) {
        QuestionCategoryDto questionCategoryDto = baseMapper.mapObject(questionCategoryEntity, QuestionCategoryDto.class);
        questionCategoryDto.getQuestions().stream()
                .forEach(q -> q.setQuestionCategoryName(questionCategoryDto.getCategoryName()));
        return questionCategoryDto;
    }

    public QuestionCategoryEntity mapToNewQuestionCategoryEntity(QuestionCategoryDto questionCategoryDto) {
        QuestionCategoryEntity questionCategoryEntity = baseMapper.mapObject(questionCategoryDto, QuestionCategoryEntity.class);
        addNewQuestionsToCategory(questionCategoryEntity, questionCategoryDto);
        return questionCategoryEntity;
    }

    public void updateQuestionCategoryEntity(QuestionCategoryEntity questionCategoryEntity,
                                                               QuestionCategoryDto questionCategoryDto) {
        questionCategoryEntity.setCommentLabel(questionCategoryDto.getCommentLabel());
        questionCategoryEntity.setDisplayDirection(questionCategoryDto.getDisplayDirection());
        questionCategoryEntity.setHasComment(questionCategoryDto.getHasComment());
        questionCategoryEntity.setSelectionType(questionCategoryDto.getSelectionType());
        removeExistingQuestionsFromCategory(questionCategoryEntity);
        addNewQuestionsToCategory(questionCategoryEntity, questionCategoryDto);
    }

    private void addNewQuestionsToCategory(QuestionCategoryEntity questionCategoryEntity, QuestionCategoryDto questionCategoryDto) {
        questionCategoryDto.getQuestions().stream()
                .forEach(q -> questionCategoryEntity.addQuestion(getQuestionEntity(q)));
    }

    private void removeExistingQuestionsFromCategory(QuestionCategoryEntity questionCategoryEntity) {
        questionCategoryEntity.getQuestions().iterator()
                .forEachRemaining(q -> questionCategoryEntity.removeQuestion(q));
    }

    private QuestionEntity getQuestionEntity(QuestionDto questionDto) {
        return questionRepository.findById(questionDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("Question not found for supplied id"));
    }
}
