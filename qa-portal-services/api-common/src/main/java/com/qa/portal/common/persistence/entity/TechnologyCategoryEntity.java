package com.qa.portal.common.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "TECHNOLOGYCATEGORY")
@Table(schema = "training", name = "technology_category")


public class TechnologyCategoryEntity extends QaBaseEntity {

   
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "technology_category_sequence")
	@SequenceGenerator(name = "technology_category_sequence",
			sequenceName = "training.technology_category_sequence",
			allocationSize = 1)
	private Integer id;
    
    @Column(name = "category_name")    
    private String categoryName;

    @Column(name = "search_string")  
    private String searchString;

    @Column(name = "last_updated_timestamp")
    private Timestamp lastUpdatedTimestamp;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

	@OneToMany(mappedBy = "technologyId", fetch = FetchType.LAZY)
	private List<TechnologyEntity> technologies;

    private Integer version;

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



    
    // todo other gets and sets

}