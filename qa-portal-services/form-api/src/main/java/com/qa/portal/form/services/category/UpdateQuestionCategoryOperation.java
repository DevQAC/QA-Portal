package com.qa.portal.form.services.category;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.form.services.category.mapper.QuestionCategoryMapper;
import org.springframework.stereotype.Component;

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
        return questionCategoryDto;
    }
}
