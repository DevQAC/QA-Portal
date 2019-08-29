package com.qa.portal.cv.domain;

import java.util.List;

public class Profile {

	private String profileDetails;
	private List<Feedback> profileFeedback;
	
	public Profile() {
		super();
	}

	public Profile(String profileDetails, List<Feedback> profileFeedback) {
		super();
		this.profileDetails = profileDetails;
		this.profileFeedback = profileFeedback;
	}

	public String getProfileDetails() {
		return profileDetails;
	}

	public void setProfileDetails(String profileDetails) {
		this.profileDetails = profileDetails;
	}

	public List<Feedback> getProfileFeedback() {
		return profileFeedback;
	}

	public void setProfileFeedback(List<Feedback> profileFeedback) {
		this.profileFeedback = profileFeedback;
	}
}
