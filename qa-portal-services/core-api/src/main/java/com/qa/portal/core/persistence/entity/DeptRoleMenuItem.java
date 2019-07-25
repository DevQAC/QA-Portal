package com.qa.portal.core.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "training", name = "dept_role_menu_item")
public class DeptRoleMenuItem {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name = "dept_role_menu_item_sequence",
            sequenceName = "training.dept_role_menu_item_sequence",
            allocationSize=1)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "dept_role_id")
    private DepartmentRoleEntity departmentRole;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItemEntity menuItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DepartmentRoleEntity getDepartmentRole() {
        return departmentRole;
    }

    public void setDepartmentRole(DepartmentRoleEntity departmentRole) {
        this.departmentRole = departmentRole;
    }

    public MenuItemEntity getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItemEntity menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptRoleMenuItem that = (DeptRoleMenuItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(departmentRole, that.departmentRole) &&
                Objects.equals(menuItem, that.menuItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentRole, menuItem);
    }

    @Override
    public String toString() {
        return "DeptRoleMenuItem{" +
                "id=" + id +
                ", departmentRole=" + departmentRole +
                ", menuItem=" + menuItem +
                '}';
    }
}
