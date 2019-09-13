package com.qa.portal.common.dto;

import java.util.List;

public class QuestionCategoryResponseDto extends QaBaseDto {

    private Integer id;

    private CommentDto comment;

    private QuestionCategoryDto questionCategory;

    private List<QuestionResponseDto> questionResponses;

    private Integer parentId;

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

    public QuestionCategoryDto getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategoryDto questionCategory) {
        this.questionCategory = questionCategory;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "QuestionCategoryResponseDto{" +
                "id=" + id +
                ", comment=" + comment +
                ", questionCategory=" + questionCategory +
                ", questionResponses=" + questionResponses +
                ", parentId=" + parentId +
                '}';
    }
}
