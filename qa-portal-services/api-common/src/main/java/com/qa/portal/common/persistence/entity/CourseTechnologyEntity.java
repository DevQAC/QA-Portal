package com.qa.portal.common.persistence.entity;

import javax.persistence.*;

@Entity
@Table(schema = "training", name = "course_technology")
public class CourseTechnologyEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "course_technology_sequence")
    @SequenceGenerator(name = "course_technology_sequence",
            sequenceName = "training.course_technology_sequence",
            allocationSize = 1)
    private Integer id;

    @ManyToOne
	@JoinColumn(name = "course_id")
	private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "technology_id")
    private TechnologyEntity technology;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public TechnologyEntity getTechnology() {
        return technology;
    }

    public void setTechnology(TechnologyEntity technology) {
        this.technology = technology;
    }
}
