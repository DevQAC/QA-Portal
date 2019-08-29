package com.qa.portal.common.persistence.entity;

//import java.util.Set;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(schema = "training", name = "technology")

public class TechnologyEntity extends QaBaseEntity {
   
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "technology_sequence")
	@SequenceGenerator(name = "technology_sequence",
			sequenceName = "training.technology_sequence",
			allocationSize = 1)

    private Integer id;
    
    @Column(name = "technology_name")    
    private String technologyName;

	@ManyToOne // many tecnologies maps to a tech category
    @JoinColumn(name = "technology_category_id")
    private TechnologyCategoryEntity technologyCategory;

	@OneToMany(mappedBy = "technology")
	private List<CourseTechnologyEntity> courseTechnology;

    @Column(name = "search_string")  
    private String searchString;

    @Column(name = "last_updated_timestamp")
    private Timestamp lastUpdatedTimestamp;

    @Column(name = "last_updated_by")
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

	public TechnologyCategoryEntity getTechnologyCategory() {
		return technologyCategory;
	}

	public void setTechnologyCategory(TechnologyCategoryEntity technologyCategory) {
		this.technologyCategory = technologyCategory;
	}

	public List<CourseTechnologyEntity> getCourseTechnology() {
		return courseTechnology;
	}

	public void setCourseTechnology(List<CourseTechnologyEntity> courseTechnology) {
		this.courseTechnology = courseTechnology;
	}
}
