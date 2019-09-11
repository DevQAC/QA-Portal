package com.qa.portal.user.dto;

import com.qa.portal.common.dto.TechnologyDto;

import java.util.List;
import java.util.Map;

public class UserSkillsDto {

    private String userName;

    private String userFirstName;

    private String userLastName;

    private Map<String, List<TechnologyDto>> skills;

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

    public Map<String, List<TechnologyDto>> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, List<TechnologyDto>> skills) {
        this.skills = skills;
    }
}
