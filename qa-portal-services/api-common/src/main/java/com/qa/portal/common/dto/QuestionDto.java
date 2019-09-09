package com.qa.portal.common.dto;

import java.util.List;

public class QuestionDto extends QaBaseDto {

    private Integer id;

    private String body;

    private Boolean hasComment;

    private String commentLabel;

    private String questionCategoryName;

    private List<String> selectionOptionsList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getHasComment() {
        return hasComment;
    }

    public void setHasComment(Boolean hasComment) {
        this.hasComment = hasComment;
    }

    public String getCommentLabel() {
        return commentLabel;
    }

    public void setCommentLabel(String commentLabel) {
        this.commentLabel = commentLabel;
    }

    public String getQuestionCategoryName() {
        return questionCategoryName;
    }

    public void setQuestionCategoryName(String questionCategoryName) {
        this.questionCategoryName = questionCategoryName;
    }

    public List<String> getSelectionOptionsList() {
        return selectionOptionsList;
    }

    public void setSelectionOptionsList(List<String> selectionOptionsList) {
        this.selectionOptionsList = selectionOptionsList;
    }
}
