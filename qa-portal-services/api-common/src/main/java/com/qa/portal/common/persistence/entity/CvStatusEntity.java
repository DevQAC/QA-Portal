package com.qa.portal.common.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.*;


@Entity
@Table(schema = "training", name = "cv_status")
public class CvStatusEntity extends QaBaseEntity {
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "cv_status_sequence")
    @SequenceGenerator(name = "cv_status_sequence",
            sequenceName = "training.cv_status_sequence",
            allocationSize = 1)
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

    @Override
    public Timestamp getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    @Override
    public void setLastUpdatedTimestamp(Timestamp lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    @Override
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }
}