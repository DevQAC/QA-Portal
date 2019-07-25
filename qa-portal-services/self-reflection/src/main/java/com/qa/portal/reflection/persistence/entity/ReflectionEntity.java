package com.qa.portal.reflection.persistence.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.qa.portal.common.persistence.entity.QaBaseEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;

@Entity
@Table(name = "reflection", schema = "training")
public class ReflectionEntity extends QaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "reflection_sequence", sequenceName = "training.reflection_sequence", allocationSize = 1)
	private Integer id;

	@OneToMany(mappedBy = "reflection")
	private Set<ReflectionQuestionEntity> reflectionQuestions;

	@ManyToOne
	@JoinColumn(name = "reviewer_id")
	private TrainerEntity reviewer;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "responder_id")
	private TraineeEntity responder;

	@Column(name = "form_date")
	private LocalDate formDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<ReflectionQuestionEntity> getReflectionQuestions() {
		return reflectionQuestions;
	}

	public void setReflectionQuestions(Set<ReflectionQuestionEntity> reflectionQuestions) {
		this.reflectionQuestions = reflectionQuestions;
	}

	public TrainerEntity getReviewer() {
		return reviewer;
	}

	public void setReviewer(TrainerEntity reviewer) {
		this.reviewer = reviewer;
	}

	public TraineeEntity getResponder() {
		return responder;
	}

	public void setResponder(TraineeEntity responder) {
		this.responder = responder;
	}

	public LocalDate getFormDate() {
		return formDate;
	}

	public void setFormDate(LocalDate formDate) {
		this.formDate = formDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ReflectionEntity that = (ReflectionEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(reflectionQuestions, that.reflectionQuestions)
				&& Objects.equals(reviewer, that.reviewer) && Objects.equals(responder, that.responder)
				&& Objects.equals(formDate, that.formDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, reflectionQuestions, reviewer, responder, formDate);
	}

	@Override
	public String toString() {
		return "ReflectionEntity{" + "id=" + id + ", reflectionQuestions=" + reflectionQuestions + ", reviewer="
				+ reviewer + ", responder=" + responder + ", formDate=" + formDate + ", lastUpdatedTimestamp="
				+ lastUpdatedTimestamp + ", lastUpdatedBy='" + lastUpdatedBy + '\'' + ", version=" + version + '}';
	}
}
