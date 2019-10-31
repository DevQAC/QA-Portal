package com.qa.portal.form.services.question;

import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.form.services.question.mapper.QuestionMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateQuestionOperation {

    private QuestionRepository questionRepository;

    private QuestionMapper questionMapper;

    public CreateQuestionOperation(QuestionRepository questionRepository,
                                   QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    @Transactional
    public QuestionDto createQuestion(QuestionDto questionDto) {
        if (questionExists(questionDto)) {
            throw new QaPortalBusinessException("Question with same text exists for same category");
        }
        QuestionEntity questionEntity = questionMapper.mapNewQuestionEntity(questionDto);
        QuestionEntity savedEntity = questionRepository.save(questionEntity);
        return questionMapper.mapToQuestionDto(savedEntity);
    }

    private boolean questionExists(QuestionDto questionDto) {
        return questionRepository.findByBody(questionDto.getBody())
                .stream()
                .filter(q -> q.getCategory().getCategoryName().equals(questionDto.getQuestionCategoryName()))
                .findAny()
                .map(q -> true)
                .orElseGet(() -> false);
    }
}
