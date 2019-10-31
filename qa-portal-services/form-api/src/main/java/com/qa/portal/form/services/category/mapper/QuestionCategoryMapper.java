package com.qa.portal.form.services.category.mapper;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.form.services.question.mapper.QuestionMapper;
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

    private QuestionMapper questionMapper;

    private BaseMapper baseMapper;

    public QuestionCategoryMapper(QuestionCategoryRepository questionCategoryRepository,
                                  QuestionRepository questionRepository,
                                  QuestionMapper questionMapper,
                                  BaseMapper baseMapper) {
        this.questionCategoryRepository = questionCategoryRepository;
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
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
        updateExistingQuestions(questionCategoryEntity, questionCategoryDto);
        removeDeletedQuestionsFromCategory(questionCategoryEntity, questionCategoryDto);
        addNewQuestionsToCategory(questionCategoryEntity, questionCategoryDto);
     }

    private void updateExistingQuestions(QuestionCategoryEntity questionCategoryEntity,
                                         QuestionCategoryDto questionCategoryDto) {
        questionCategoryEntity.getQuestions().stream()
                .filter(q -> getNewQuestions(questionCategoryDto).contains(q.getId()))
                .forEach(q -> questionMapper.mapUpdatedQuestionEntity(q, getQuestionDto(questionCategoryDto, q)));
    }

    private void removeDeletedQuestionsFromCategory(QuestionCategoryEntity questionCategoryEntity,
                                                    QuestionCategoryDto questionCategoryDto) {
        questionCategoryEntity.getQuestions().stream()
                .filter(q -> !getNewQuestions(questionCategoryDto).contains(q.getId()))
                .collect(Collectors.toList())
                .iterator()
                .forEachRemaining(q -> questionCategoryEntity.removeQuestion(q));
    }

    private void addNewQuestionsToCategory(QuestionCategoryEntity questionCategoryEntity,
                                           QuestionCategoryDto questionCategoryDto) {
        questionCategoryDto.getQuestions().stream()
                .filter(q -> q.getId() == null)
                .forEach(q -> questionCategoryEntity.addQuestion(questionMapper.mapNewQuestionEntity(q)));
    }

    private QuestionDto getQuestionDto(QuestionCategoryDto questionCategoryDto, QuestionEntity questionEntity) {
        return questionCategoryDto.getQuestions().stream()
                .filter(q -> questionEntity.getId().equals(q.getId()))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No question found for the supplied id"));
    }

    private List<Integer> getNewQuestions(QuestionCategoryDto questionCategoryDto) {
        return questionCategoryDto.getQuestions().stream()
                .map(q -> q.getId())
                .collect(Collectors.toList());
    }
}
