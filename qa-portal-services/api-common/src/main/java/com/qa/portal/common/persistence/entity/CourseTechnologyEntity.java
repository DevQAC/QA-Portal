package com.qa.portal.common.persistence.entity;
import java.sql.Timestamp;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value = "COURSETECHNOLOGY")
@Table(schema = "training", name = "course_technology")


public class CourseTechnologyEntity extends QaBaseEntity {

   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer id;
    
    @Column(name = "course_id")    
    private Integer courseId;

    @Column(name = "technology_id")   
    private Integer technologyId;

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

    public Integer getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTechnologyId() {
        return this.technologyId;
    }

    public void setTechnologyId(Integer techId) {
        this.technologyId = techId;
    }
    
    // todo other gets and sets

}