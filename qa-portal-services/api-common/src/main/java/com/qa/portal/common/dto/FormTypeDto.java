package com.qa.portal.common.dto;

import java.util.List;

public class FormTypeDto extends QaBaseDto {

    private Integer id;

    private String formName;

    private String description;

    private List<QuestionCategoryDto> questionCategories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public List<QuestionCategoryDto> getQuestionCategories() {
        return questionCategories;
    }

    public void setQuestionCategories(List<QuestionCategoryDto> questionCategories) {
        this.questionCategories = questionCategories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
