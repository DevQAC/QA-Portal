package com.qa.portal.common.dto;

import java.util.List;

public class CourseDto extends QaBaseDto {

    private Integer id;

    private String courseName;

    private String courseCode;

    private List<CourseTechnologyDto> technologies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<CourseTechnologyDto> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<CourseTechnologyDto> technologies) {
        this.technologies = technologies;
    }

}
