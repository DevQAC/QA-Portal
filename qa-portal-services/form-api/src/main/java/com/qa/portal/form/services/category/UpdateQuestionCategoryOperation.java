package com.qa.portal.form.services.category;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.form.services.category.mapper.QuestionCategoryMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateQuestionCategoryOperation {

    private QuestionCategoryRepository questionCategoryRepository;

    private QuestionCategoryMapper questionCategoryMapper;

    public UpdateQuestionCategoryOperation(QuestionCategoryRepository questionCategoryRepository,
                                           QuestionCategoryMapper questionCategoryMapper) {
        this.questionCategoryRepository = questionCategoryRepository;
        this.questionCategoryMapper = questionCategoryMapper;
    }

    public QuestionCategoryDto updateQuestionCategory(QuestionCategoryDto questionCategoryDto) {
        return getQuestionCategory(questionCategoryDto)
                .map(qc -> persistUpdatedQuestionCategory(qc, questionCategoryDto))
                .orElseThrow(() -> new QaPortalBusinessException("Question Category not found for supplied category name"));
    }

    private QuestionCategoryDto persistUpdatedQuestionCategory(QuestionCategoryEntity questionCategoryEntity,
                                                               QuestionCategoryDto questionCategoryDto) {
        questionCategoryMapper.updateQuestionCategoryEntity(questionCategoryEntity, questionCategoryDto);
        return questionCategoryMapper.createQuestionCategoryDto(questionCategoryEntity);
    }

    private Optional<QuestionCategoryEntity> getQuestionCategory(QuestionCategoryDto questionCategoryDto) {
        return questionCategoryRepository.findByCategoryName(questionCategoryDto.getCategoryName());
    }
}
