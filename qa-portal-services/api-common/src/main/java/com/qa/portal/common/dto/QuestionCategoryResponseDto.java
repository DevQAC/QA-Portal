package com.qa.portal.common.dto;

public class QuestionCategoryResponseDto {

    private Integer id;

    private CommentDto comment;

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
}
