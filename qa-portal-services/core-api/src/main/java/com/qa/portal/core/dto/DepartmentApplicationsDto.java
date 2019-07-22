package com.qa.portal.core.dto;

import java.util.Objects;
import java.util.Set;

public class DepartmentApplicationsDto {

    private DepartmentDto department;

    private Set<ApplicationDto>  applications;

    public DepartmentApplicationsDto(DepartmentDto department, Set<ApplicationDto> applications) {
        this.department = department;
        this.applications = applications;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public Set<ApplicationDto> getApplications() {
        return applications;
    }

    public void setApplications(Set<ApplicationDto> applications) {
        this.applications = applications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentApplicationsDto that = (DepartmentApplicationsDto) o;
        return Objects.equals(department, that.department) &&
                Objects.equals(applications, that.applications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, applications);
    }

    @Override
    public String toString() {
        return "DepartmentApplicationsDto{" +
                "department=" + department +
                ", applications=" + applications +
                '}';
    }
}
