package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "training", name = "question_category_response")
@DiscriminatorColumn(name = "discriminator")
public abstract class QuestionCategoryResponseEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "question_category_response_sequence")
    @SequenceGenerator(name = "question_category_response_sequence",
            sequenceName = "training.question_category_response_sequence",
            allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "question_category_id")
    private QuestionCategoryEntity questionCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private CommentEntity comment;

    @OneToMany(mappedBy = "categoryResponse", cascade = CascadeType.ALL)
    private List<QuestionResponseEntity> questionResponses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<QuestionResponseEntity> getQuestionCategories() {
        return questionResponses;
    }

    public void setQuestionCategories(List<QuestionResponseEntity> questionResponses) {
        this.questionResponses = questionResponses;
    }

    public QuestionCategoryEntity getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategoryEntity questionCategory) {
        this.questionCategory = questionCategory;
    }

    public List<QuestionResponseEntity> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(List<QuestionResponseEntity> questionResponses) {
        this.questionResponses = questionResponses;
    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }
}
