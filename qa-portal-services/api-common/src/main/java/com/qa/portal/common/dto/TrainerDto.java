package com.qa.portal.common.dto;

import java.util.Set;

public class TrainerDto extends QaUserDto {

    private Set<QaCohortDto> cohorts;

    public Set<QaCohortDto> getCohorts() {
        return cohorts;
    }

    public void setCohorts(Set<QaCohortDto> cohorts) {
        this.cohorts = cohorts;
    }
}
