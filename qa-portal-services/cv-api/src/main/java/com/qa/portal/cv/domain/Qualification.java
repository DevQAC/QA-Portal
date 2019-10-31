package com.qa.portal.cv.domain;

import java.util.List;

public class Qualification {
	
	private String qualificationDetails;

	private List<Feedback> qualificationFeedback;
	
	public Qualification() {
		super();
	}

	public String getQualificationDetails() {
		return qualificationDetails;
	}

	public void setQualificationDetails(String qualificationDetails) {
		this.qualificationDetails = qualificationDetails;
	}

	public List<Feedback> getQualificationFeedback() {
		return qualificationFeedback;
	}

	public void setQualificationFeedback(List<Feedback> qualificationFeedback) {
		this.qualificationFeedback = qualificationFeedback;
	}

	@Override
	public String toString() {
		return "Qualification [qualificationDetails=" + qualificationDetails + ", qualificationFeedback="
				+ qualificationFeedback + "]";
	}
}
