package com.qa.portal.reflection.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qa.portal.common.dto.QaBaseDto;
import com.qa.portal.common.dto.QaUserDto;

public final class ReflectionDto extends QaBaseDto {

	private final Integer id;

	private final QaUserDto responder;

	private final QaUserDto reviewer;

	private final LocalDate date;

	private Set<QuestionDto> questions;

	@JsonCreator
	public ReflectionDto(@JsonProperty Integer id, @JsonProperty QaUserDto responder, @JsonProperty QaUserDto reviewer,
			@JsonProperty LocalDate date) {
		super();
		this.id = id;
		this.responder = responder;
		this.reviewer = reviewer;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public QaUserDto getResponder() {
		return responder;
	}

	public QaUserDto getReviewer() {
		return reviewer;
	}

	public LocalDate getDate() {
		return date;
	}

	public Set<QuestionDto> getQuestions() {
		return Collections.unmodifiableSet(this.questions);
	}

}
