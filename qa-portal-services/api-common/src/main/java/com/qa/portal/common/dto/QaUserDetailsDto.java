package com.qa.portal.common.dto;

import java.util.List;

public class QaUserDetailsDto {

    private QaUserDto user;

    private List<String> roleNames;

    private List<String> cohortNames;

    public QaUserDto getUser() {
        return user;
    }

    public void setUser(QaUserDto user) {
        this.user = user;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public List<String> getCohortNames() {
        return cohortNames;
    }

    public void setCohortNames(List<String> cohortNames) {
        this.cohortNames = cohortNames;
    }
}
