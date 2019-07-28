package com.qa.portal.reflection.dto;

import com.qa.portal.common.dto.QaBaseDto;

public class ReflectionQuestionDto extends QaBaseDto {

	private Integer id;

	private ReflectionDto reflection;

	private QuestionDto question;

	private Integer response;

	private Integer trainerResponse;

	public ReflectionQuestionDto(Integer id, ReflectionDto reflection, QuestionDto question, Integer response,
			Integer trainerResponse) {
		super();
		this.id = id;
		this.reflection = reflection;
		this.question = question;
		this.response = response;
		this.trainerResponse = trainerResponse;
	}

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

}