package com.qa.portal.core.dto;

import java.util.List;
import java.util.Objects;

public class PortalProjectDto {

    private Integer id;

    private String name;

    private String url;

    private List<ProjectPageDto> projectPages;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ProjectPageDto> getProjectPages() {
        return projectPages;
    }

    public void setProjectPages(List<ProjectPageDto> projectPages) {
        this.projectPages = projectPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortalProjectDto that = (PortalProjectDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(projectPages, that.projectPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, projectPages);
    }
}
