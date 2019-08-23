package com.qa.portal.cv.domain;

import java.util.List;

public class Profile {

	private String details;
	private List<Feedback> profileFeedback;
	
	public Profile() {
		super();
	}

	public Profile(String details, List<Feedback> profileFeedback) {
		super();
		this.details = details;
		this.profileFeedback = profileFeedback;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public List<Feedback> getProfileFeedback() {
		return profileFeedback;
	}

	public void setProfileFeedback(List<Feedback> profileFeedback) {
		this.profileFeedback = profileFeedback;
	}
}
