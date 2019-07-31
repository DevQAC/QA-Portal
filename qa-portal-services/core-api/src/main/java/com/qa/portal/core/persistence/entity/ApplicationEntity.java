package com.qa.portal.core.persistence.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "training", name = "application")
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "application_sequence")
    @SequenceGenerator(name = "application_sequence",
            sequenceName = "training.application_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @OneToMany(mappedBy = "application")
    private List<MenuItemEntity> menuItems;

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

    public List<MenuItemEntity> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemEntity> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationEntity that = (ApplicationEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(url, that.url) &&
                Objects.equals(menuItems, that.menuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url, menuItems);
    }

    @Override
    public String toString() {
        return "ApplicationEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", menuItems=" + menuItems +
                '}';
    }
}
