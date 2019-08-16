package com.qa.portal.feedback.services.mapper;

import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.service.mapper.QuestionCategoryResponseMapper;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.EvalQuestionCategoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EvaluationQuestionCategoryResponseMapper extends QuestionCategoryResponseMapper<EvalQuestionCategoryResponseDto> {
    public EvaluationQuestionCategoryResponseMapper(BaseMapper baseMapper) {
        super(baseMapper);
    }

    public EvalQuestionCategoryResponseDto createFeedbackQuestionCategoryResponseDto(QuestionCategoryEntity questionCategoryEntity) {
        EvalQuestionCategoryResponseDto evalQuestionCategoryResponseDto = new EvalQuestionCategoryResponseDto();
        return createQuestionCategoryResponseDto(questionCategoryEntity, evalQuestionCategoryResponseDto);
    }
}
