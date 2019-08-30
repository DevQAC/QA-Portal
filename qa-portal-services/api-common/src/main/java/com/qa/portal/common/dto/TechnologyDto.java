package com.qa.portal.common.dto;

import java.sql.Timestamp;
import java.util.List;

public class TechnologyDto {

    private Integer id;

    private String technologyName ;

    private TechnologyCategoryDto technologyCategory;

    private List<CourseTechnologyDto> courseTechnologies;

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

    public TechnologyCategoryDto getTechnologyCategory() {
        return technologyCategory;
    }

    public void setTechnologyCategory(TechnologyCategoryDto technologyCategory) {
        this.technologyCategory = technologyCategory;
    }

    public List<CourseTechnologyDto> getCourseTechnologies() {
        return courseTechnologies;
    }

    public void setCourseTechnologies(List<CourseTechnologyDto> courseTechnologies) {
        this.courseTechnologies = courseTechnologies;
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
