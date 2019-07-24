package com.qa.portal.common.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
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
