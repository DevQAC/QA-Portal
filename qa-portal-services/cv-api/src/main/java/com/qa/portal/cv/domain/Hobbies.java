package com.qa.portal.cv.domain;

import java.util.List;

public class Hobbies {

    private String hobbiesDetails;

    private List<Feedback> hobbiesFeedback;

    public Hobbies() {
        super();
    }

    public String getHobbiesDetails() {
        return hobbiesDetails;
    }

    public void setHobbiesDetails(String hobbiesDetails) {
        this.hobbiesDetails = hobbiesDetails;
    }

    public List<Feedback> getHobbiesFeedback() {
        return hobbiesFeedback;
    }

    public void setHobbiesFeedback(List<Feedback> hobbiesFeedback) {
        this.hobbiesFeedback = hobbiesFeedback;
    }


}
