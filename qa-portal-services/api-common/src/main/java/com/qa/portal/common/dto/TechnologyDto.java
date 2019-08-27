package com.qa.portal.common.dto;

import java.sql.Timestamp;

public class TechnologyDto {

    private Integer id;

    private String technologyName ;

    private Integer technologyCategoryId ;

    private String searchString;

    private Timestamp lastUpdatedTimestamp;

    private String lastUpdatedBy;

    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

    public Integer getTechnologyCategoryId() {
        return technologyCategoryId;
    }

    public void setTechnologyCategoryId(Integer technologyCategoryId) {
        this.technologyCategoryId = technologyCategoryId;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "TechnologyDto{" +
                "id=" + id +
                ", technologyName='" + technologyName + '\'' +
                ", technologyCategoryId=" + technologyCategoryId +
                ", searchString='" + searchString + '\'' +
                ", lastUpdatedTimestamp=" + lastUpdatedTimestamp +
                ", lastUpdatedBy=" + lastUpdatedBy +
                ", version=" + version +
                '}';
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
