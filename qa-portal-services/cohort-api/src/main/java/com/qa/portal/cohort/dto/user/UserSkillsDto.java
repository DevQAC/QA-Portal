package com.qa.portal.cohort.dto.user;

import com.qa.portal.common.dto.TechnologyDto;

import java.util.Map;
import java.util.Set;

public class UserSkillsDto {

    private String userName;

    private String userFirstName;

    private String userLastName;

    private Map<String, Set<TechnologyDto>> skills;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Map<String, Set<TechnologyDto>> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Set<TechnologyDto>> skills) {
        this.skills = skills;
    }
}
