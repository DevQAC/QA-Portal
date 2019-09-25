package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "training", name = "question")
public class QuestionEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "question_sequence")
    @SequenceGenerator(name = "question_sequence",
            sequenceName = "training.question_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "body")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private QuestionCategoryEntity category;

    @Column(name = "has_comment")
    private Boolean hasComment;

    @Column(name = "comment_label")
    private String commentLabel;

    @Column(name = "selection_options")
    private String selectionOptionsList;

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

    public QuestionCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(QuestionCategoryEntity category) {
        this.category = category;
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

    public String getSelectionOptionsList() {
        return selectionOptionsList;
    }

    public void setSelectionOptionsList(String selectionOptionsList) {
        this.selectionOptionsList = selectionOptionsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionEntity that = (QuestionEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
