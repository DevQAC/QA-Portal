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

import java.util.List;
import java.util.stream.Collectors;

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
        LOGGER.info("Category is " + questionCategoryDto.getCategoryName());
        questionCategoryDto.getQuestions().stream()
                .forEach(q -> q.setQuestionCategoryName(questionCategoryDto.getCategoryName()));
        return questionCategoryDto;
    }

    public QuestionCategoryEntity mapToNewQuestionCategoryEntity(QuestionCategoryDto questionCategoryDto) {
        QuestionCategoryEntity questionCategoryEntity = baseMapper.mapObject(questionCategoryDto, QuestionCategoryEntity.class);
        questionCategoryEntity.setQuestions(getQuestionsForNewCategory(questionCategoryDto));
        return questionCategoryEntity;
    }

    public QuestionCategoryEntity mapToUpdatedQuestionCategoryEntity(QuestionCategoryDto questionCategoryDto) {
        QuestionCategoryEntity questionCategoryEntity = baseMapper.mapObject(questionCategoryDto, QuestionCategoryEntity.class);
        questionCategoryEntity.setQuestions(getQuestionsForNewCategory(questionCategoryDto));
        return questionCategoryEntity;
    }

    public List<QuestionEntity> getQuestionsForNewCategory(QuestionCategoryDto questionCategoryDto) {
        return questionCategoryDto.getQuestions().stream()
                .map(qDto -> getQuestionEntity(qDto))
                .collect(Collectors.toList());
    }

    private void addNewQuestionsToCategory() {

    }

    private void removeExistingQuestionsFromCategory() {

    }

    private QuestionEntity getQuestionEntity(QuestionDto questionDto) {
        return questionRepository.findById(questionDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("Question not found for supplied id"));
    }
}
