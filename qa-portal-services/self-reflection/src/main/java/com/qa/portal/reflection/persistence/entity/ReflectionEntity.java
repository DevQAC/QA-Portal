package com.qa.portal.reflection.persistence.entity;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.qa.portal.common.persistence.entity.QaBaseEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;

@Entity
@Table(name = "reflection", schema = "training")
public class ReflectionEntity extends QaBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "reflection_sequence")
    @SequenceGenerator(name = "reflection_sequence",
            sequenceName = "training.reflection_sequence",
            allocationSize = 1)
    private Integer id;

    @OneToMany(mappedBy = "reflection", cascade = CascadeType.ALL)
    private Set<ReflectionQuestionEntity> reflectionQuestions;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "reviewer_id")
    private TrainerEntity reviewer;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "responder_id")
    private TraineeEntity responder;

    @Column(name = "form_date")
    private Date formDate;

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

    @Column(name = "status")
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


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

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReflectionEntity that = (ReflectionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(reflectionQuestions, that.reflectionQuestions) &&
                Objects.equals(reviewer, that.reviewer) &&
                Objects.equals(responder, that.responder) &&
                Objects.equals(formDate, that.formDate) &&
                Objects.equals(trainerFeedback, that.trainerFeedback) &&
                Objects.equals(learningPathway, that.learningPathway) &&
                Objects.equals(strengths, that.strengths) &&
                Objects.equals(weaknesses, that.weaknesses) &&
                Objects.equals(opportunities, that.opportunities) &&
                Objects.equals(threats, that.threats) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reflectionQuestions, reviewer, responder, formDate, trainerFeedback, learningPathway, strengths, weaknesses, opportunities, threats, status);
    }

    @Override
    public String toString() {
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
                ", status='" + status + '\'' +
                '}';
    }
}
