package com.qa.portal.cv.domain;

// Class for the search criteria selected on the admin search page.
// Currently Cohort, Status, Technology and Name
public class CvSearchCriteria {

    private String cvStatus;

    private String FullName;

    private String cohort;

    private String technology;

    public CvSearchCriteria(String cvStatus, String fullName, String cohort, String technology) {
        this.cvStatus = cvStatus;
        FullName = fullName;
        this.cohort = cohort;
        this.technology = technology;
    }

    public String getCvStatus() {
        return cvStatus;
    }

    public void setCvStatus(String cvStatus) {
        this.cvStatus = cvStatus;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "CvSearchCriteria{" +
                "cvStatus='" + cvStatus + '\'' +
                ", FullName='" + FullName + '\'' +
                ", cohort='" + cohort + '\'' +
                ", technology='" + technology + '\'' +
                '}';
    }
}
