package com.qa.portal.form.services.category;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.form.services.category.mapper.QuestionCategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class RemoveQuestionFromCategoryOperation {

    private QuestionRepository questionRepository;

    private QuestionCategoryMapper questionCategoryMapper;

    public RemoveQuestionFromCategoryOperation(QuestionRepository questionRepository,
                                               QuestionCategoryMapper questionCategoryMapper) {
        this.questionRepository = questionRepository;
        this.questionCategoryMapper = questionCategoryMapper;
    }

    public QuestionCategoryDto removeQuestionFromCategory(Integer questionId) {
        return questionRepository.findById(questionId)
                .map(q -> removeQuestion(q))
                .orElseThrow(() -> new QaPortalBusinessException("Question not found for supplied id"));
    }

    private QuestionCategoryDto removeQuestion(QuestionEntity questionEntity) {
        QuestionCategoryEntity questionCategoryEntity = questionEntity.getCategory();
        questionCategoryEntity.removeQuestion(questionEntity);
        return questionCategoryMapper.createQuestionCategoryDto(questionCategoryEntity);
    }
}
