package com.qa.portal.common.dto;

import com.qa.portal.common.dto.QaUserDto;

public class QaUserDetailsDto {

    private QaUserDto user;

    private String roleName;

    private String cohortName;

    public QaUserDto getUser() {
        return user;
    }

    public void setUser(QaUserDto user) {
        this.user = user;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }
}
