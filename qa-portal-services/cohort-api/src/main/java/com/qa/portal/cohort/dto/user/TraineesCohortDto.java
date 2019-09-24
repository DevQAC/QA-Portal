package com.qa.portal.cohort.dto.user;

import java.util.List;

public class TraineesCohortDto {

    private List<String> traineeNames;

    private String cohortName;

    public List<String> getTraineeNames() {
        return traineeNames;
    }

    public void setTraineeNames(List<String> traineeNames) {
        this.traineeNames = traineeNames;
    }

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }
}
