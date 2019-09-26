package com.qa.portal.application.dto;

import java.util.Objects;
import java.util.Set;

public class ApplicationProjectsDto {

    private PortalApplicationDto portalApplication;

    private Set<PortalProjectDto> portalProjects;

    public ApplicationProjectsDto(PortalApplicationDto portalApplication, Set<PortalProjectDto> portalProjects) {
        this.portalApplication = portalApplication;
        this.portalProjects = portalProjects;
    }

    public PortalApplicationDto getPortalApplication() {
        return portalApplication;
    }

    public void setPortalApplication(PortalApplicationDto portalApplication) {
        this.portalApplication = portalApplication;
    }

    public Set<PortalProjectDto> getPortalProjects() {
        return portalProjects;
    }

    public void setPortalProjects(Set<PortalProjectDto> portalProjects) {
        this.portalProjects = portalProjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationProjectsDto that = (ApplicationProjectsDto) o;
        return Objects.equals(portalApplication, that.portalApplication) &&
                Objects.equals(portalProjects, that.portalProjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portalApplication, portalProjects);
    }

    @Override
    public String toString() {
        return "DepartmentApplicationsDto{" +
                "department=" + portalApplication +
                ", applications=" + portalProjects +
                '}';
    }
}
