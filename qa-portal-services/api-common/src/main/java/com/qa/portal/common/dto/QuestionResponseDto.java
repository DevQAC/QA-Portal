package com.qa.portal.common.dto;

public class QuestionResponseDto {
    private Integer id;

    private QuestionDto question;

    private CommentDto comment;

    private List<String> responseValues; //TO DO (List of Strings)
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
    
    public void setResponseValues(list<String> responseValues) {
        this.responseValues = responseValues;
    }
}
