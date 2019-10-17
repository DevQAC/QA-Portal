package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "technology_category_id")
    private TechnologyCategoryEntity technologyCategory;

    @OneToMany(mappedBy = "technology")
    private List<CourseTechnologyEntity> courseTechnologyList;

    @Column(name = "search_string")
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

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public TechnologyCategoryEntity getTechnologyCategory() {
        return technologyCategory;
    }

    public void setTechnologyCategory(TechnologyCategoryEntity technologyCategory) {
        this.technologyCategory = technologyCategory;
    }

    public List<CourseTechnologyEntity> getCourseTechnologyList() {
        return courseTechnologyList;
    }

    public void setCourseTechnologyList(List<CourseTechnologyEntity> courseTechnologyList) {
        this.courseTechnologyList = courseTechnologyList;
    }
}
