package com.qa.portal.feedback.services.mapper;

import com.qa.portal.common.dto.QuestionCategoryResponseDto;
import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.dto.QuestionResponseDto;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.service.mapper.QuestionCategoryResponseMapper;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.FeedbackQuestionCategoryResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FeedbackQuestionCategoryResponseMapper extends QuestionCategoryResponseMapper<FeedbackQuestionCategoryResponseDto> {
    public FeedbackQuestionCategoryResponseMapper(BaseMapper baseMapper) {
        super(baseMapper);
    }

    public FeedbackQuestionCategoryResponseDto createFeedbackQuestionCategoryResponseDto(QuestionCategoryEntity questionCategoryEntity) {
        FeedbackQuestionCategoryResponseDto feedbackQuestionCategoryResponseDto = new FeedbackQuestionCategoryResponseDto();
        feedbackQuestionCategoryResponseDto.setQuestionResponses(getQuestionResponseDtos(feedbackQuestionCategoryResponseDto));
        return createQuestionCategoryResponseDto(questionCategoryEntity, feedbackQuestionCategoryResponseDto);
    }

    private List<QuestionResponseDto> getQuestionResponseDtos(QuestionCategoryResponseDto questionCategoryResponseDto) {
        return questionCategoryResponseDto.getQuestionCategory().getQuestions()
                .stream()
                .map(q -> createQuestionResponseDto(q))
                .collect(Collectors.toList());
    }

    private QuestionResponseDto createQuestionResponseDto(QuestionDto questionDto) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setQuestion(questionDto);
        return questionResponseDto;
    }
}
