package com.qa.portal.application.persistence.entity;

import com.qa.portal.common.persistence.entity.QaBaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "training", name = "project_page")
public class ProjectPageEntity extends QaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "app_menu_item_sequence")
    @SequenceGenerator(name = "app_menu_item_sequence",
            sequenceName = "training.app_menu_item_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "tooltip")
    private String tooltip;

    @Column(name = "icon")
    private String icon;

    @ManyToOne
    @JoinColumn(name = "portal_project_id")
    private PortalProjectEntity portalProject;

    @OneToMany(mappedBy = "projectPage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleProjectPageEntity> roleProjectPageEntities;

    @Column(name = "display_on_menu")
    private Boolean displayOnMenu;


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

    public PortalProjectEntity getPortalProject() {
        return portalProject;
    }

    public void setPortalProject(PortalProjectEntity portalProject) {
        this.portalProject = portalProject;
    }

    public List<RoleProjectPageEntity> getRoleProjectPageEntities() {
        return roleProjectPageEntities;
    }

    public void setRoleProjectPageEntities(List<RoleProjectPageEntity> roleProjectPageEntities) {
        this.roleProjectPageEntities = roleProjectPageEntities;
    }

    public void addRoleProjectPageEntity(RoleProjectPageEntity roleProjectPageEntity) {
        if (roleProjectPageEntities == null) {
            roleProjectPageEntities = new ArrayList<>();
        }
        roleProjectPageEntities.add(roleProjectPageEntity);
        roleProjectPageEntity.setProjectPage(this);
    }

    public void removeRoleProjectPageEntity(RoleProjectPageEntity roleProjectPageEntity) {
        roleProjectPageEntities.remove(roleProjectPageEntity);
        roleProjectPageEntity.setProjectPage(null);
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
        ProjectPageEntity that = (ProjectPageEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(tooltip, that.tooltip) &&
                Objects.equals(icon, that.icon) &&
                Objects.equals(portalProject, that.portalProject) &&
                Objects.equals(roleProjectPageEntities, that.roleProjectPageEntities) &&
                Objects.equals(displayOnMenu, that.displayOnMenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, tooltip, icon, portalProject, roleProjectPageEntities, displayOnMenu);
    }

    @Override
    public String toString() {
        return "ProjectPageEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", tooltip='" + tooltip + '\'' +
                ", icon='" + icon + '\'' +
                ", portalProject=" + portalProject +
                ", displayOnMenu=" + displayOnMenu +
                '}';
    }
}
