package com.qa.portal.common.persistence.entity;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "TECHNOLOGYCATEGORY")
@Table(schema = "training", name = "technology_category")


public class TechnologyCategoryEntity extends QaBaseEntity {

   
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer id;
    
    @Column(name = "category_name")    
    private String categoryName;

    @Column(name = "search_string")  
    private String searchString;

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

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    // todo other gets and sets

}