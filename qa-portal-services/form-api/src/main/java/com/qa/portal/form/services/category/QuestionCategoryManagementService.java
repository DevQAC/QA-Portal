package com.qa.portal.form.services.category;

import com.qa.portal.common.dto.QuestionCategoryDto;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionCategoryManagementService {

    private CreateQuestionCategoryOperation createQuestionCategoryOperation;

    private UpdateQuestionCategoryOperation updateQuestionCategoryOperation;

    public QuestionCategoryManagementService(CreateQuestionCategoryOperation createQuestionCategoryOperation,
                                             UpdateQuestionCategoryOperation updateQuestionCategoryOperation) {
        this.createQuestionCategoryOperation = createQuestionCategoryOperation;
        this.updateQuestionCategoryOperation = updateQuestionCategoryOperation;
    }

    @Transactional
    public QuestionCategoryDto createQuestionCategory(QuestionCategoryDto questionCategoryDto) {
        return createQuestionCategoryOperation.createQuestionCategory(questionCategoryDto);
    }

    @Transactional
    public QuestionCategoryDto updateQuestionCategory(QuestionCategoryDto questionCategoryDto) {
        return updateQuestionCategoryOperation.updateQuestionCategory(questionCategoryDto);
    }
}
