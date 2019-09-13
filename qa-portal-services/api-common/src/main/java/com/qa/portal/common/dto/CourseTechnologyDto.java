package com.qa.portal.common.dto;

public class CourseTechnologyDto extends QaBaseDto {

    private Integer id;

    private CourseDto course;

    private TechnologyDto technology;

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
}
