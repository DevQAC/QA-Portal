package com.qa.portal.common.persistence.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
@Entity
public class TrainerEntity extends QaUserEntity {

	@OneToMany
	@JoinColumn(name = "trainer_id")
	private Set<QaCohortEntity> cohorts;

	public Set<QaCohortEntity> getCohorts() {
		return cohorts;
	}

	public void setCohorts(Set<QaCohortEntity> cohorts) {
		this.cohorts = cohorts;
	}
	
	
	
	
}
