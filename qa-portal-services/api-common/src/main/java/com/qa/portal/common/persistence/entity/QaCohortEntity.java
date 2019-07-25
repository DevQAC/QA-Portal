package com.qa.portal.common.persistence.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "training", name = "qa_cohort")
public class QaCohortEntity extends QaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "qa_cohort_sequence")
	@SequenceGenerator(name = "qa_cohort_sequence", sequenceName = "qa_cohort_sequence")
	private Integer id;

	@Column(name = "cohort_name")
	private String name;

	@OneToMany(mappedBy = "cohort")
	private Set<TraineeEntity> trainees;

	@ManyToOne
	@JoinColumn(name = "trainer_id")
	private TrainerEntity trainer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TraineeEntity> getTrainees() {
		return trainees;
	}

	public void setTrainees(Set<TraineeEntity> trainees) {
		this.trainees = trainees;
	}

	public TrainerEntity getTrainer() {
		return trainer;
	}

	public void setTrainer(TrainerEntity trainer) {
		this.trainer = trainer;
	}
}
