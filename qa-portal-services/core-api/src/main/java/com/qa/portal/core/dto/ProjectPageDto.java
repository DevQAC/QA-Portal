package com.qa.portal.core.dto;

import java.util.Objects;

public class ProjectPageDto {

    private Integer id;

    private String name;

    private String url;

    private String tooltip;

    private Integer level;

    private Boolean displayOnMenu;

    private String icon;

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

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getDisplayOnMenu() {
        return displayOnMenu;
    }

    public void setDisplayOnMenu(Boolean displayOnMenu) {
        this.displayOnMenu = displayOnMenu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectPageDto that = (ProjectPageDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(tooltip, that.tooltip) &&
                Objects.equals(level, that.level) &&
                Objects.equals(displayOnMenu, that.displayOnMenu) &&
                Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, tooltip, level, displayOnMenu, icon);
    }
}
