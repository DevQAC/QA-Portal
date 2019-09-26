package com.qa.portal.application.persistence.entity;

import com.qa.portal.common.persistence.entity.QaBaseEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "training", name = "role")
public class RoleEntity extends QaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "role_sequence")
    @SequenceGenerator(name = "role_sequence",
            sequenceName = "training.role_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "portal_application_id")
    private PortalApplicationEntity portalApplication;

    @OneToMany(mappedBy = "role")
    private List<RoleProjectPageEntity> roleProjectPages;

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

    public PortalApplicationEntity getPortalApplication() {
        return portalApplication;
    }

    public void setPortalApplication(PortalApplicationEntity portalApplication) {
        this.portalApplication = portalApplication;
    }

    public List<RoleProjectPageEntity> getRoleProjectPages() {
        return roleProjectPages;
    }

    public void setRoleProjectPages(List<RoleProjectPageEntity> roleProjectPages) {
        this.roleProjectPages = roleProjectPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(portalApplication, that.portalApplication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, portalApplication);
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", portalApplication=" + portalApplication +
                '}';
    }
}
