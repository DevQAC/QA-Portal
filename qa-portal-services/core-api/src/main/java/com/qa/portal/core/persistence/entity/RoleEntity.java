package com.qa.portal.core.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema="training", name="role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_sequence",
            sequenceName = "training.role_sequence",
            allocationSize=1)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="level")
    private Integer level;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level);
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
