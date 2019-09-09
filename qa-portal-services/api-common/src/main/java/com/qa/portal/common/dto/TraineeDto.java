package com.qa.portal.common.dto;

public final class TraineeDto extends QaUserDto {

    private QaCohortDto cohort;

    public QaCohortDto getCohort() {
        return cohort;
    }

    public void setCohort(QaCohortDto cohort) {
        this.cohort = cohort;
    }
}
