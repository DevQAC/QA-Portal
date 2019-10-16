package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
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

	@OneToMany(mappedBy = "technologyCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TechnologyEntity> technologies;

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

	public List<TechnologyEntity> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<TechnologyEntity> technologies) {
		this.technologies = technologies;
	}

	public void addTechnology(TechnologyEntity technologyEntity) {
		if (technologies == null) {
			technologies = new ArrayList<>();
		}
		technologies.add(technologyEntity);
		technologyEntity.setTechnologyCategory(this);
	}

	public void removeTechnology(TechnologyEntity technologyEntity) {
		technologies.remove(technologyEntity);
		technologyEntity.setTechnologyCategory(null);
	}
}
