package com.qa.portal.common.persistence.entity;

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
}
