package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "training", name = "question_category")
public class QuestionCategoryEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "question_category_sequence")
    @SequenceGenerator(name = "question_category_sequence",
            sequenceName = "training.question_category_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "has_comment")
    private Boolean hasComment;

    @Column(name = "comment_label")
    private String commentLabel;

    @Column(name = "display_direction")
    private String displayDirection;

    @Column(name = "selection_type")
    private String selectionType;

    @ManyToOne
    @JoinColumn(name = "form_type_id")
    private FormTypeEntity formType;

    @OneToMany(mappedBy = "category")
    private List<QuestionEntity> questions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getDisplayDirection() {
        return displayDirection;
    }

    public void setDisplayDirection(String displayDirection) {
        this.displayDirection = displayDirection;
    }

    public String getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(String selectionType) {
        this.selectionType = selectionType;
    }

    public FormTypeEntity getFormType() {
        return formType;
    }

    public void setFormType(FormTypeEntity formType) {
        this.formType = formType;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }
}
