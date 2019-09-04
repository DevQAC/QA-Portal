package com.qa.portal.cv.domain;

import com.qa.portal.common.security.QaSecurityContext;

import java.util.Set;

// The identifying details retrieved from the security context
// saved in Keycloak
public class CvUserDetails {

    private String firstName;
    private String lastName;
    private String userName;
    private String cohort;




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public static CvUserDetails retrieveCvUserDetails(QaSecurityContext securityContext){

        CvUserDetails user = new CvUserDetails();
        user.setFirstName(securityContext.getFirstName());
        user.setLastName(securityContext.getSurname());
        user.setUserName(securityContext.getUserName());
        Set<String> cohorts = securityContext.getCohorts();
        String cohort = "";
        if (cohorts.toArray().length > 0) cohort = (String) cohorts.toArray()[0];
        user.setCohort(cohort);
        return user;

    }
}
