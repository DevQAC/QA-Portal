package com.qa.portal.common.dto;

import java.sql.Timestamp;

public class TechnologyCategoryDto {

    private Integer id;

    private String  categoryName;

    private String searchString;

    private Timestamp lastUpdatedTimestamp;

    private String lastUpdatedBy;

    private Integer version;

    @java.lang.Override
    public java.lang.String toString() {
        return "TechnologyCategoryDto{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", searchString='" + searchString + '\'' +
                ", lastUpdatedTimestamp=" + lastUpdatedTimestamp +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", version=" + version +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
