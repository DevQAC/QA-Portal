package com.qa.portal.core.persistence.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(schema="training", name="dept_role")
public class DepartmentRoleEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,
            generator = "dept_role_sequence")
    @SequenceGenerator(name = "dept_role_sequence",
            sequenceName = "training.dept_role_sequence",
            allocationSize=1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="dept_id")
    private DepartmentEntity department;

    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleEntity role;

    @OneToMany(mappedBy = "departmentRole")
    private List<DepartmentRoleApplicationEntity> deptRoleApplications;

    @OneToMany(mappedBy = "departmentRole")
    private List<DeptRoleMenuItem> deptRoleMenuItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public List<DepartmentRoleApplicationEntity> getDeptRoleApplications() {
        return deptRoleApplications;
    }

    public void setDeptRoleApplications(List<DepartmentRoleApplicationEntity> deptRoleApplications) {
        this.deptRoleApplications = deptRoleApplications;
    }

    public List<DeptRoleMenuItem> getDeptRoleMenuItems() {
        return deptRoleMenuItems;
    }

    public void setDeptRoleMenuItems(List<DeptRoleMenuItem> deptRoleMenuItems) {
        this.deptRoleMenuItems = deptRoleMenuItems;
    }

    public String getDepartmentRoleName() {
        return this.role.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentRoleEntity that = (DepartmentRoleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(department, that.department) &&
                Objects.equals(role, that.role) &&
                Objects.equals(deptRoleApplications, that.deptRoleApplications) &&
                Objects.equals(deptRoleMenuItems, that.deptRoleMenuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, department, role, deptRoleApplications, deptRoleMenuItems);
    }

    @Override
    public String toString() {
        return "DepartmentRoleEntity{" +
                "id=" + id +
                ", department=" + department +
                ", role=" + role +
                ", deptRoleApplications=" + deptRoleApplications +
                ", deptRoleMenuItems=" + deptRoleMenuItems +
                '}';
    }
}
