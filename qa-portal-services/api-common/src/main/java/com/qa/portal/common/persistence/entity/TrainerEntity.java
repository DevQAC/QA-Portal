package com.qa.portal.common.persistence.entity;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value = "TRAINER")
public class TrainerEntity extends QaUserEntity {

    @OneToMany(mappedBy = "trainer")
    private Set<QaCohortEntity> cohorts;

    public Set<QaCohortEntity> getCohorts() {
        return cohorts;
    }

    public void setCohorts(Set<QaCohortEntity> cohorts) {
        this.cohorts = cohorts;
    }
}
