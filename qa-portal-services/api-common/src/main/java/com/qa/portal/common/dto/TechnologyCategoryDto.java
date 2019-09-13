package com.qa.portal.common.dto;

public class TechnologyCategoryDto extends QaBaseDto {

    private Integer id;

    private String  categoryName;

    private String searchString;

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

    @Override
    public String toString() {
        return "TechnologyCategoryDto{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", searchString='" + searchString + '\'' +
                '}';
    }
}
