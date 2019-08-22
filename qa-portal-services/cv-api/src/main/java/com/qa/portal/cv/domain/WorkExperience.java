package com.qa.portal.cv.domain;

import java.util.List;

public class WorkExperience {

	private String jobTitle;
	private String start;
	private String end;
	private String workExperienceDetails;
	private List<Feedback> workExperienceFeedback;

	public WorkExperience(String jobTitle, String start, String end, String workExperienceDetails,
			List<Feedback> workExperienceFeedback) {
		super();
		this.jobTitle = jobTitle;
		this.start = start;
		this.end = end;
		this.workExperienceDetails = workExperienceDetails;
		this.workExperienceFeedback = workExperienceFeedback;
	}
	

	public WorkExperience() {
		super();
	}
	
	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public String getStart() {
		return start;
	}


	public void setStart(String start) {
		this.start = start;
	}


	public String getEnd() {
		return end;
	}


	public void setEnd(String end) {
		this.end = end;
	}


	public String getWorkExperienceDetails() {
		return workExperienceDetails;
	}


	public void setWorkExperienceDetails(String workExperienceDetails) {
		this.workExperienceDetails = workExperienceDetails;
	}

	public List<Feedback> getWorkExperienceFeedback() {
		return workExperienceFeedback;
	}
	
	public void setWorkExperienceFeedback(List<Feedback> workExperienceFeedback) {
		this.workExperienceFeedback = workExperienceFeedback;
	}
}
