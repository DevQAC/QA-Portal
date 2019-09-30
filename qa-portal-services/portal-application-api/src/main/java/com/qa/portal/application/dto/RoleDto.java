package com.qa.portal.application.dto;

import java.util.Objects;

public class RoleDto {

    private Integer id;

    private String name;

    private PortalApplicationDto portalApplication;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PortalApplicationDto getPortalApplication() {
        return portalApplication;
    }

    public void setPortalApplication(PortalApplicationDto portalApplication) {
        this.portalApplication = portalApplication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(id, roleDto.id) &&
                Objects.equals(name, roleDto.name) &&
                Objects.equals(portalApplication, roleDto.portalApplication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, portalApplication);
    }
}
