package com.qa.portal.common.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "training", name = "form_type")
public class FormTypeEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "form_type_sequence")
    @SequenceGenerator(name = "form_type_sequence",
            sequenceName = "training.form_type_sequence",
            allocationSize = 1)
    private Integer id;

    @Column(name = "form_name")
    private String formName;

    @OneToMany(mappedBy = "formType", cascade = CascadeType.ALL)
    private List<QuestionCategoryEntity> questionCategories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public List<QuestionCategoryEntity> getQuestionCategories() {
        return questionCategories;
    }

    public void setQuestionCategories(List<QuestionCategoryEntity> questionCategories) {
        this.questionCategories = questionCategories;
    }
}
