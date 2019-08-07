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
//
//	@JsonCreator
//	public QaTrainerDto(@JsonProperty Integer id, @JsonProperty String userName, @JsonProperty Set<QaCohortDto> cohorts) {
//		super(id, userName);
//		this.cohorts = cohorts;
//	}

    public TrainerDto() {

    }

}
