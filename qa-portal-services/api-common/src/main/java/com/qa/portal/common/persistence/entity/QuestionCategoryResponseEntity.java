package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "training", name = "question_category_response")
@DiscriminatorColumn(name = "discriminator")
public abstract class QuestionCategoryResponseEntity<T> extends QaBaseEntity {

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

    public QuestionCategoryEntity getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategoryEntity questionCategory) {
        this.questionCategory = questionCategory;
    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }

    public List<QuestionResponseEntity> getQuestionResponses() {
        return questionResponses;
    }

    public void setQuestionResponses(List<QuestionResponseEntity> questionResponses) {
        this.questionResponses = questionResponses;
    }

    public abstract T getParent();

    public abstract void setParent(T parent);

    @Override
	public String toString() {
		return "QuestionCategoryResponseEntity [id=" + id + ", questionCategory=" + questionCategory + ", comment="
				+ comment + ", questionResponses=" + questionResponses + "]";
	}
}
