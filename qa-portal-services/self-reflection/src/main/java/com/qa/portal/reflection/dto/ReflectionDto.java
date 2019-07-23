package com.qa.portal.reflection.dto;

import java.time.LocalDate;

import com.qa.portal.common.dto.QaBaseDto;
import com.qa.portal.common.dto.QaUserDto;

public final class ReflectionDto extends QaBaseDto {
	
	private final Integer id;
	
	private final QaUserDto responder;
	
	private final QaUserDto reviewer;
	
	private final LocalDate date;

	public ReflectionDto(Integer id, QaUserDto responder, QaUserDto reviewer, LocalDate date) {
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
	
	

}
