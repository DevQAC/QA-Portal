package com.qa.portal.form.services.category;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.form.services.category.mapper.QuestionCategoryMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateQuestionCategoryOperation {

    private QuestionCategoryRepository questionCategoryRepository;

    private QuestionCategoryMapper questionCategoryMapper;

    public CreateQuestionCategoryOperation(QuestionCategoryRepository questionCategoryRepository,
                                           QuestionCategoryMapper questionCategoryMapper) {
        this.questionCategoryRepository = questionCategoryRepository;
        this.questionCategoryMapper = questionCategoryMapper;
    }

    public QuestionCategoryDto createQuestionCategory(QuestionCategoryDto questionCategoryDto) {
        if (questionCategoryExists(questionCategoryDto)) {
            throw new QaPortalBusinessException("Question Category with the supplied name already exists");
        }
        QuestionCategoryEntity questionCategoryEntity = questionCategoryMapper.mapToNewQuestionCategoryEntity(questionCategoryDto);
        QuestionCategoryEntity savedCategory = questionCategoryRepository.save(questionCategoryEntity);
        return questionCategoryMapper.createQuestionCategoryDto(savedCategory);
    }

    private boolean questionCategoryExists(QuestionCategoryDto questionCategoryDto) {
        return questionCategoryRepository.findByCategoryName(questionCategoryDto.getCategoryName())
                .map(qc -> true)
                .orElseGet(() -> false);
    }
}
