package com.qa.portal.common.dto;

public class QuestionResponseDto {
    private Integer id;

    private QuestionDto question;

    private CommentDto comment;

    private String responseValues;

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

    public String getResponseValues() {
        return responseValues;
    }

    public void setResponseValues(String responseValues) {
        this.responseValues = responseValues;
    }
}
