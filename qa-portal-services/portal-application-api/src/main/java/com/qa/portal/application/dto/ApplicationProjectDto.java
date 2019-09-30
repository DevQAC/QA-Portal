package com.qa.portal.application.dto;

public class ApplicationProjectDto {
    private PortalApplicationDto portalApplicationDto;

    private PortalProjectDto portalProjectDto;

    public ApplicationProjectDto(PortalApplicationDto portalApplicationDto, PortalProjectDto portalProjectDto) {
        this.portalApplicationDto = portalApplicationDto;
        this.portalProjectDto = portalProjectDto;
    }

    public String getPortalApplicationName() {
        return portalApplicationDto.getName();
    }

    public PortalApplicationDto getPortalApplication() {
        return portalApplicationDto;
    }

    public PortalProjectDto getPortalProjectDto() {
        return portalProjectDto;
    }

    public ApplicationProjectDto getApplicationProject() {
        return this;
    }
}
