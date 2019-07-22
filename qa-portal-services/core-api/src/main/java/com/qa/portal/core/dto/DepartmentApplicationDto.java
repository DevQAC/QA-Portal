package com.qa.portal.core.dto;

public class DepartmentApplicationDto {
    private DepartmentDto departmentDto;

    private ApplicationDto applicationDto;

    public DepartmentApplicationDto(DepartmentDto department, ApplicationDto application) {
        this.departmentDto = department;
        this.applicationDto = application;
    }

    public String getDepartmentName() {
        return departmentDto.getName();
    }

    public DepartmentDto getDepartment() {
        return departmentDto;
    }

    public ApplicationDto getApplicationDto() {
        return applicationDto;
    }

    public DepartmentApplicationDto getDepartmentApplicationDto() {
        return this;
    }
}
