package com.qa.portal.common.dto;

import java.util.List;

public class QuestionResponseDto extends QaBaseDto {

    private Integer id;

    private QuestionDto question;

    private CommentDto comment;

    private List<String> responseValues;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuestionDto getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDto question) {
        this.question = question;
    }

    public CommentDto getComment() {
        return comment;
    }

    public void setComment(CommentDto comment) {
        this.comment = comment;
    }

    public List<String> getResponseValues() {
        return responseValues;
    }

    public void setResponseValues(List<String> responseValues) {
        this.responseValues = responseValues;
    }
}
