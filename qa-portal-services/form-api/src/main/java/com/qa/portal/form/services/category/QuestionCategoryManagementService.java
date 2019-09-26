package com.qa.portal.form.services.category;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.dto.QuestionDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionCategoryManagementService {

    private CreateQuestionCategoryOperation createQuestionCategoryOperation;

    private UpdateQuestionCategoryOperation updateQuestionCategoryOperation;

    private RemoveQuestionFromCategoryOperation removeQuestionFromCategoryOperation;

    public QuestionCategoryManagementService(CreateQuestionCategoryOperation createQuestionCategoryOperation,
                                             UpdateQuestionCategoryOperation updateQuestionCategoryOperation,
                                             RemoveQuestionFromCategoryOperation removeQuestionFromCategoryOperation) {
        this.createQuestionCategoryOperation = createQuestionCategoryOperation;
        this.updateQuestionCategoryOperation = updateQuestionCategoryOperation;
        this.removeQuestionFromCategoryOperation = removeQuestionFromCategoryOperation;
    }

    @Transactional
    public QuestionCategoryDto createQuestionCategory(QuestionCategoryDto questionCategoryDto) {
        return createQuestionCategoryOperation.createQuestionCategory(questionCategoryDto);
    }

    @Transactional
    public QuestionCategoryDto updateQuestionCategory(QuestionCategoryDto questionCategoryDto) {
        return updateQuestionCategoryOperation.updateQuestionCategory(questionCategoryDto);
    }

    @Transactional
    public QuestionCategoryDto removeQuestionFromQuestionCategory(Integer questionId) {
        return removeQuestionFromCategoryOperation.removeQuestionFromCategory(questionId);
    }
}
