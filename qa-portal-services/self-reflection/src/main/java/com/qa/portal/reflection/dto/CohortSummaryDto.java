package com.qa.portal.reflection.dto;

import java.util.List;

import com.qa.portal.common.dto.QaBaseDto;

public class CohortSummaryDto extends QaBaseDto {

	private String cohortName;

	private List<ReflectionQuestionDto> scores;

	public CohortSummaryDto(String cohortName, List<ReflectionQuestionDto> scores) {
		super();
		this.cohortName = cohortName;
		this.scores = scores;
	}

	public String getCohortName() {
		return cohortName;
	}

	public void setCohortName(String cohortName) {
		this.cohortName = cohortName;
	}

	public List<ReflectionQuestionDto> getScores() {
		return scores;
	}

	public void setScores(List<ReflectionQuestionDto> scores) {
		this.scores = scores;
	}

}
