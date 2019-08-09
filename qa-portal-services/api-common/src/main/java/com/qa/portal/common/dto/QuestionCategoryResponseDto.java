package com.qa.portal.common.dto;

import java.util.List;

public class QuestionCategoryResponseDto {

    private Integer id;

    private CommentDto comment;

    private List<QuestionResponseDto> questionResponses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CommentDto getComment() {
        return comment;
    }

    public void setComment(CommentDto comment) {
        this.comment = comment;
    }

    public List<QuestionResponseDto> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(List<QuestionResponseDto> questionResponses) {
        this.questionResponses = questionResponses;
    }
}
