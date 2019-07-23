package com.qa.portal.common.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class TraineeEntity extends QaUserEntity {
	
	@ManyToOne
	@JoinColumn(name = "cohort_id")
	private QaCohortEntity cohort;

	public QaCohortEntity getCohort() {
		return cohort;
	}

	public void setCohort(QaCohortEntity cohort) {
		this.cohort = cohort;
	}

}
