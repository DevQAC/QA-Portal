package com.qa.portal.common.persistence.entity;
import java.sql.Timestamp;

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

    @Override
    public Timestamp getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    @Override
    public void setLastUpdatedTimestamp(Timestamp lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    @Override
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    @Override
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }
}