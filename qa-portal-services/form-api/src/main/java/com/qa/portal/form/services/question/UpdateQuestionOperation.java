package com.qa.portal.form.services.question;

import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.form.services.question.mapper.QuestionMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateQuestionOperation {

    private QuestionRepository questionRepository;

    private QuestionMapper questionMapper;

    public UpdateQuestionOperation(QuestionRepository questionRepository,
                                   QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public QuestionDto updateQuestion(QuestionDto questionDto) {
        return getQuestion(questionDto)
                .map(q -> updateQuestion(q, questionDto))
                .orElseThrow(() -> new QaPortalBusinessException("Question not found for supplied id"));
    }

    private QuestionDto updateQuestion(QuestionEntity questionEntity, QuestionDto questionDto) {
        QuestionEntity updatedEntity = questionMapper.mapUpdatedQuestionEntity(questionEntity, questionDto);
        return questionMapper.mapToQuestionDto(updatedEntity);
    }

    private Optional<QuestionEntity> getQuestion(QuestionDto questionDto) {
        return questionRepository.findById(questionDto.getId());
    }
}
