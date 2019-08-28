package com.qa.portal.common.persistence.entity;

import javax.persistence.*;

@Entity
@Table(schema = "training", name = "question_response")
public class QuestionResponseEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "question_response_sequence")
    @SequenceGenerator(name = "question_response_sequence",
            sequenceName = "training.question_response_sequence",
            allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "question_category_response_id")
    private QuestionCategoryResponseEntity categoryResponse;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private CommentEntity comment;

    @Column(name = "response_values")
    private String responseValues;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuestionCategoryResponseEntity getCategoryResponse() {
        return categoryResponse;
    }

    public void setCategoryResponse(QuestionCategoryResponseEntity categoryResponse) {
        this.categoryResponse = categoryResponse;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public CommentEntity getComment() {
        return comment;
    }

    public void setComment(CommentEntity comment) {
        this.comment = comment;
    }

    public String getResponseValues() {
        return responseValues;
    }

    public void setResponseValues(String responseValues) {
        this.responseValues = responseValues;
    }
}
