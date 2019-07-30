package com.qa.portal.reflection.persistence.entity;

import com.qa.portal.common.persistence.entity.QaBaseEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "reflection", schema = "training")
public class ReflectionEntity extends QaBaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "reflection_sequence", sequenceName = "training.reflection_sequence", allocationSize = 1)
	private Integer id;

	@OneToMany(mappedBy = "reflection")
	private Set<ReflectionQuestionEntity> reflectionQuestions;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "reviewer_id")
	private TrainerEntity reviewer;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "responder_id")
	private TraineeEntity responder;

	@Column(name = "form_date")
	private LocalDate formDate;

	@Column(name = "trainer_feedback")
	private String trainerFeedback;

	@Column(name = "learning_pathway")
	private String learningPathway;

	@Column(name = "strengths")
	private String strengths;

	@Column(name = "weaknesses")
	private String weaknesses;

	@Column(name = "opportunities")
	private String opportunities;

	@Column(name = "threats")
	private String threats;

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

	public String getTrainerFeedback() {
		return trainerFeedback;
	}

	public void setTrainerFeedback(String trainerFeedback) {
		this.trainerFeedback = trainerFeedback;
	}

	public String getLearningPathway() {
		return learningPathway;
	}

	public void setLearningPathway(String learningPathway) {
		this.learningPathway = learningPathway;
	}

	public String getStrengths() {
		return strengths;
	}

	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}

	public String getWeaknesses() {
		return weaknesses;
	}

	public void setWeaknesses(String weaknesses) {
		this.weaknesses = weaknesses;
	}

	public String getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(String opportunities) {
		this.opportunities = opportunities;
	}

	public String getThreats() {
		return threats;
	}

	public void setThreats(String threats) {
		this.threats = threats;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		ReflectionEntity that = (ReflectionEntity) object;
		return java.util.Objects.equals(id, that.id) &&
				java.util.Objects.equals(reflectionQuestions, that.reflectionQuestions) &&
				java.util.Objects.equals(reviewer, that.reviewer) &&
				java.util.Objects.equals(responder, that.responder) &&
				java.util.Objects.equals(formDate, that.formDate) &&
				java.util.Objects.equals(trainerFeedback, that.trainerFeedback) &&
				java.util.Objects.equals(learningPathway, that.learningPathway) &&
				java.util.Objects.equals(strengths, that.strengths) &&
				java.util.Objects.equals(weaknesses, that.weaknesses) &&
				java.util.Objects.equals(opportunities, that.opportunities) &&
				java.util.Objects.equals(threats, that.threats);
	}
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), id, reflectionQuestions, reviewer, responder, formDate, trainerFeedback, learningPathway, strengths, weaknesses, opportunities, threats);
	}

	@Override
	public java.lang.String toString() {
		return "ReflectionEntity{" +
				"id=" + id +
				", reflectionQuestions=" + reflectionQuestions +
				", reviewer=" + reviewer +
				", responder=" + responder +
				", formDate=" + formDate +
				", trainerFeedback='" + trainerFeedback + '\'' +
				", learningPathway='" + learningPathway + '\'' +
				", strengths='" + strengths + '\'' +
				", weaknesses='" + weaknesses + '\'' +
				", opportunities='" + opportunities + '\'' +
				", threats='" + threats + '\'' +
				'}';
	}
}
