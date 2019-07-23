package com.qa.portal.reflection.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qa.portal.common.persistence.entity.QaBaseEntity;

@Entity
@Table(name = "reflection_question", schema = "training")
public class ReflectionQuestionEntity extends QaBaseEntity {

	@Id
	@ManyToOne
	private ReflectionEntity reflection;

	@Id
	@ManyToOne
	private QuestionEntity question;

	@Column(name = "response")
	private Integer response;

	@Column(name = "trainer_response")
	private Integer trainerResponse;

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
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((reflection == null) ? 0 : reflection.hashCode());
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
		return "ReflectionQuestionEntity [reflection=" + reflection + ", question=" + question + ", response="
				+ response + ", trainerResponse=" + trainerResponse + "]";
	}

}
