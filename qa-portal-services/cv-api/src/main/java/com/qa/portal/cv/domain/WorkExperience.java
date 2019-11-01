package com.qa.portal.cv.domain;

import java.util.List;

public class WorkExperience {

    private String jobTitle;


    private String workExperienceDetails;

    private List<Feedback> workExperienceFeedback;

    public WorkExperience() {
        super();
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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
