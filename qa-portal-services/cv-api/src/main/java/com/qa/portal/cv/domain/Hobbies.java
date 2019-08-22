package com.qa.portal.cv.domain;

import java.util.List;

public class Hobbies {

	
	private String hobbies;
	private List<Feedback> hobbiesFeedback;
	
	public Hobbies() {
		super();
	}	
	
	public Hobbies(String hobbies, List<Feedback> hobbiesFeedback) {
		super();
		this.hobbies = hobbies;
		this.hobbiesFeedback = hobbiesFeedback;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public List<Feedback> getHobbiesFeedback() {
		return hobbiesFeedback;
	}

	public void setHobbiesFeedback(List<Feedback> hobbiesFeedback) {
		this.hobbiesFeedback = hobbiesFeedback;
	}

	
}
