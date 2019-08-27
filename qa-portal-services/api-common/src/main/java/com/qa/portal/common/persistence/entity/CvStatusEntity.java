package com.qa.portal.common.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value = "CVSTATUS")
@Table(schema = "training", name = "cv_status")


public class CvStatusEntity extends QaBaseEntity {

   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer id;
    
    @Column(name = "status_name")    
    private String statusName;

    @Column(name = "last_updated_timestamp")
    private Timestamp lastUpdatedTimestamp;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    private Integer version;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusName() {
        return this.statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
    // todo other gets and sets

}