package com.qa.portal.reflection.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.qa.portal.common.persistence.entity.QaBaseEntity;

import java.util.Objects;

@Entity
@Table(name = "reflection_question", schema = "training")
public class ReflectionQuestionEntity extends QaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reflection_question_sequence")
	@SequenceGenerator(name = "reflection_question_sequence", sequenceName = "training.reflection_question_sequence", allocationSize = 1)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "reflection_id")
	private ReflectionEntity reflection;

	@ManyToOne(cascade = CascadeType.ALL)
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		// result = prime * result + ((reflection == null) ? 0 : reflection.hashCode());
		result = prime * result + ((response == null) ? 0 : response.hashCode());
		result = prime * result + ((trainerResponse == null) ? 0 : trainerResponse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReflectionQuestionEntity other = (ReflectionQuestionEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (reflection == null) {
			if (other.reflection != null)
				return false;
		} else if (!reflection.equals(other.reflection))
			return false;
		if (response == null) {
			if (other.response != null)
				return false;
		} else if (!response.equals(other.response))
			return false;
		if (trainerResponse == null) {
			if (other.trainerResponse != null)
				return false;
		} else if (!trainerResponse.equals(other.trainerResponse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		// return "ReflectionQuestionEntity [id=" + id + ", reflection=" + reflection +
		// ", question=" + question
		return "ReflectionQuestionEntity [id=" + id + ", question=" + question + ", response=" + response
				+ ", trainerResponse=" + trainerResponse + "]";
	}
}
