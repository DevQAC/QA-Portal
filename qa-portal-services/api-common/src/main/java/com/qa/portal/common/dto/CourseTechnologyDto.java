package com.qa.portal.common.dto;

import java.sql.Timestamp;

public class CourseTechnologyDto {

    private Integer id;
    private CourseDto course;
    private TechnologyDto technology;
    private Timestamp lastUpdatedTimestamp ;
    private String lastUpdatedBy;
    private Integer version;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CourseDto getCourse() {
        return course;
    }

   public TechnologyDto getTechnology() {
        return technology;
    }

    public void setTechnology(TechnologyDto technology) {
        this.technology = technology;
    }

    public Timestamp getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    public void setLastUpdatedTimestamp(Timestamp lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
