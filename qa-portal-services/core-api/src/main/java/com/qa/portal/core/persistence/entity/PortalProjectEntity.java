package com.qa.portal.core.persistence.entity;

import com.qa.portal.common.persistence.entity.QaBaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "training", name = "portal_project")
public class PortalProjectEntity extends QaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "application_sequence")
    @SequenceGenerator(name = "application_sequence",
            sequenceName = "training.application_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "portalProject")
    private List<ProjectPageEntity> projectPages;

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

    public List<ProjectPageEntity> getProjectPages() {
        return projectPages;
    }

    public void setProjectPages(List<ProjectPageEntity> projectPages) {
        this.projectPages = projectPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PortalProjectEntity that = (PortalProjectEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(projectPages, that.projectPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, projectPages);
    }

    @Override
    public String toString() {
        return "ApplicationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menuItems=" + projectPages +
                '}';
    }
}
