package com.qa.portal.reflection.dto;

import com.qa.portal.common.dto.QaBaseDto;
import com.qa.portal.common.dto.QuestionDto;

import java.util.Objects;

public class ReflectionQuestionDto extends QaBaseDto {

	private Integer id;

	private ReflectionDto reflection;

	private Integer reflectionId;

	private QuestionDto question;

	private Integer response;

	private Integer trainerResponse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReflectionDto getReflection() {
		return reflection;
	}

	public void setReflection(ReflectionDto reflection) {
		this.reflection = reflection;
	}

	public QuestionDto getQuestion() {
		return question;
	}

	public void setQuestion(QuestionDto question) {
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

	public Integer getReflectionId() {
		return reflectionId;
	}

	public void setReflectionId(Integer reflectionId) {
		this.reflectionId = reflectionId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReflectionQuestionDto that = (ReflectionQuestionDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(question, that.question) &&
                Objects.equals(response, that.response) &&
                Objects.equals(trainerResponse, that.trainerResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, response, trainerResponse);
    }

    @Override
    public String toString() {
        return "ReflectionQuestionDto{" +
                "id=" + id +
                ", question=" + question +
                ", response=" + response +
                ", trainerResponse=" + trainerResponse +
                ", lastUpdatedTimestamp=" + lastUpdatedTimestamp +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                '}';
    }
}
