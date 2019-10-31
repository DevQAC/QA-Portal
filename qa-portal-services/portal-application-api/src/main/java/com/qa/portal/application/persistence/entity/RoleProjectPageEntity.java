package com.qa.portal.application.persistence.entity;

import com.qa.portal.common.persistence.entity.QaBaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "training", name = "role_project_page")
public class RoleProjectPageEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "dept_role_menu_item_sequence")
    @SequenceGenerator(name = "dept_role_menu_item_sequence",
            sequenceName = "training.dept_role_menu_item_sequence",
            allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "project_page_id")
    private ProjectPageEntity projectPage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public ProjectPageEntity getProjectPage() {
        return projectPage;
    }

    public void setProjectPage(ProjectPageEntity projectPage) {
        this.projectPage = projectPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleProjectPageEntity that = (RoleProjectPageEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(role, that.role) &&
                Objects.equals(projectPage, that.projectPage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, projectPage);
    }

    @Override
    public String toString() {
        return "DeptRoleMenuItem{" +
                "id=" + id +
                ", departmentRole=" + role +
                ", menuItem=" + projectPage +
                '}';
    }
}
