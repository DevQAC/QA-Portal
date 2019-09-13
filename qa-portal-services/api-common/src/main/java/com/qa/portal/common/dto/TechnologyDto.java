package com.qa.portal.common.dto;

import java.util.List;

public class TechnologyDto extends QaBaseDto {

    private Integer id;

    private String technologyName ;

    private TechnologyCategoryDto technologyCategory;

    private List<CourseTechnologyDto> courseTechnologies;

    private String searchString;

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

    public String getTechnologyCategoryName() {
        return technologyCategory.getSearchString();
    }
}
