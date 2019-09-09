package com.qa.portal.common.persistence.entity;

import javax.persistence.*;

@Entity
@Table(schema = "training", name = "location")
public class LocationEntity extends QaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "location_sequence")
    @SequenceGenerator(name = "location_sequence",
            sequenceName = "training.location_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
