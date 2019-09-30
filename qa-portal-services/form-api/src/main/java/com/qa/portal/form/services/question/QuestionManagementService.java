package com.qa.portal.form.services.question;

import com.qa.portal.common.dto.QuestionDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionManagementService {

    private CreateQuestionOperation createQuestionOperation;

    private UpdateQuestionOperation updateQuestionOperation;

    public QuestionManagementService(CreateQuestionOperation createQuestionOperation,
                                     UpdateQuestionOperation updateQuestionOperation) {
        this.createQuestionOperation = createQuestionOperation;
        this.updateQuestionOperation = updateQuestionOperation;
    }

    @Transactional
    public QuestionDto createQuestion(QuestionDto questionDto) {
        return createQuestionOperation.createQuestion(questionDto);
    }

    @Transactional
    public QuestionDto updateQuestion(QuestionDto questionDto) {
        return updateQuestionOperation.updateQuestion(questionDto);
    }
}
