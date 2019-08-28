package com.qa.portal.common.persistence.entity;
import java.sql.Timestamp;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value = "COURSETECHNOLOGY")
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

    // for reference
//	@ManyToOne
//	@JoinColumn(name = "course_id")
//	private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "technology_id")
    private TechnologyEntity technology;

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