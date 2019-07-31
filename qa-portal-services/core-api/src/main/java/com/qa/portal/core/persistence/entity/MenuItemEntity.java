package com.qa.portal.core.persistence.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "training", name = "app_menu_item")
public class MenuItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "app_menu_item_sequence")
    @SequenceGenerator(name = "app_menu_item_sequence",
            sequenceName = "training.app_menu_item_sequence",
            allocationSize=1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "tooltip")
    private String tooltip;

    @ManyToOne
    @JoinColumn(name = "app_id")
    private ApplicationEntity application;

    @OneToMany(mappedBy = "menuItem")
    private List<DeptRoleMenuItem> deptRoleMenuItems;


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

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

    public List<DeptRoleMenuItem> getDeptRoleMenuItems() {
        return deptRoleMenuItems;
    }

    public void setDeptRoleMenuItems(List<DeptRoleMenuItem> deptRoleMenuItems) {
        this.deptRoleMenuItems = deptRoleMenuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItemEntity that = (MenuItemEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(tooltip, that.tooltip) &&
                Objects.equals(application, that.application) &&
                Objects.equals(deptRoleMenuItems, that.deptRoleMenuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, tooltip, application, deptRoleMenuItems);
    }

    @Override
    public String toString() {
        return "MenuItemEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", tooltip='" + tooltip + '\'' +
                ", deptRoleMenuItems=" + deptRoleMenuItems +
                '}';
    }
}
