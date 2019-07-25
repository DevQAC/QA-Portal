package com.qa.portal.reflection.dto;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qa.portal.common.dto.QaBaseDto;
import com.qa.portal.common.dto.QaTraineeDto;
import com.qa.portal.common.dto.QaTrainerDto;

public final class ReflectionDto extends QaBaseDto {

	private Integer id;

	private QaTraineeDto responder;

	private QaTrainerDto reviewer;

	private Date date;

	private Set<QuestionDto> questions;
	
	@JsonCreator
	public ReflectionDto(@JsonProperty Integer id, @JsonProperty QaTraineeDto responder, @JsonProperty QaTrainerDto reviewer,
			@JsonProperty Date date, @JsonProperty Set<QuestionDto> questions) {
		super();
		this.id = id;
		this.responder = responder;
		this.reviewer = reviewer;
		this.date = date;
		this.setQuestions(questions);
	}
	
	public ReflectionDto() {
		this.setQuestions(null);
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public QaTraineeDto getResponder() {
		return responder;
	}

	public QaTrainerDto getReviewer() {
		return reviewer;
	}

	public Date getDate() {
		return date;
	}

	public void setResponder(QaTraineeDto responder) {
		this.responder = responder;
	}
	
	public void setReviewer(QaTrainerDto reviewer) {
		this.reviewer = reviewer;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setQuestions(Set<QuestionDto> questions) {
		this.questions = Optional.ofNullable(questions).orElse(new HashSet<QuestionDto>());
	}
	
	public Set<QuestionDto> getQuestions() {
		return Collections.unmodifiableSet(this.questions);
	}

}
