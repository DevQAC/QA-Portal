package com.qa.portal.reflection.persistence.entity;

import javax.persistence.*;

import com.qa.portal.common.persistence.entity.QaBaseEntity;
import com.qa.portal.common.persistence.entity.QuestionEntity;

import java.util.Objects;

@Entity
@Table(name = "reflection_question", schema = "training")
public class ReflectionQuestionEntity extends QaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator="reflection_question_sequence")
	@SequenceGenerator(name = "reflection_question_sequence",
			sequenceName = "training.reflection_question_sequence",
			allocationSize = 1)
	private Integer id;

    @ManyToOne
    @JoinColumn(name = "reflection_id")
    private ReflectionEntity reflection;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @Column(name = "response")
    private Integer response;

    @Column(name = "trainer_response")
    private Integer trainerResponse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReflectionEntity getReflection() {
        return reflection;
    }

    public void setReflection(ReflectionEntity reflection) {
        this.reflection = reflection;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public Integer getResponse() {
        return response;
    }

    public void setResponse(Integer response) {
        this.response = response;
    }

    public Integer getTrainerResponse() {
        return trainerResponse;
    }

    public void setTrainerResponse(Integer trainerResponse) {
        this.trainerResponse = trainerResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReflectionQuestionEntity that = (ReflectionQuestionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(reflection, that.reflection) &&
                Objects.equals(question, that.question) &&
                Objects.equals(response, that.response) &&
                Objects.equals(trainerResponse, that.trainerResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, response, trainerResponse);
    }
}
