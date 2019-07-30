package com.qa.portal.reflection.dto;

import com.qa.portal.common.dto.QaBaseDto;
import com.qa.portal.common.dto.QaUserDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class ReflectionDto extends QaBaseDto {

    private Integer id;

    private QaUserDto responder;

    private QaUserDto reviewer;

    private Date formDate;

    private String trainerFeedback;

    private String learningPathway;

    private String strengths;

    private String weaknesses;

    private String opportunities;

    private String threats;


    private Set<ReflectionQuestionDto> reflectionQuestions = new HashSet<ReflectionQuestionDto>();

    public Integer getId() {
        return id;
    }

    public QaUserDto getResponder() {
        return responder;
    }

    public QaUserDto getReviewer() {
        return reviewer;
    }

    public Date getFormDate() {
        return formDate;
    }


    public String getLearningPathway() {
        return learningPathway;
    }

    public Set<ReflectionQuestionDto> getQuestions() {
        return Collections.unmodifiableSet(this.reflectionQuestions);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setResponder(QaUserDto responder) {
        this.responder = responder;
    }

    public void setReviewer(QaUserDto reviewer) {
        this.reviewer = reviewer;
    }

    public void setFormDate(Date date) {
        this.formDate = date;
    }

    public void setLearningPathway(String learningPathway) {
        this.learningPathway = learningPathway;
    }

    public void setQuestions(Set<ReflectionQuestionDto> questions) {
        this.reflectionQuestions = questions;
    }

    public String getTrainerFeedback() {
        return trainerFeedback;
    }

    public void setTrainerFeedback(String trainerFeedback) {
        this.trainerFeedback = trainerFeedback;
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

    public Set<ReflectionQuestionDto> getReflectionQuestions() {
        return reflectionQuestions;
    }

    public void setReflectionQuestions(Set<ReflectionQuestionDto> reflectionQuestions) {
        this.reflectionQuestions = reflectionQuestions;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        ReflectionDto that = (ReflectionDto) object;
        return java.util.Objects.equals(id, that.id) &&
                java.util.Objects.equals(responder, that.responder) &&
                java.util.Objects.equals(reviewer, that.reviewer) &&
                java.util.Objects.equals(formDate, that.formDate) &&
                java.util.Objects.equals(trainerFeedback, that.trainerFeedback) &&
                java.util.Objects.equals(learningPathway, that.learningPathway) &&
                java.util.Objects.equals(strengths, that.strengths) &&
                java.util.Objects.equals(weaknesses, that.weaknesses) &&
                java.util.Objects.equals(opportunities, that.opportunities) &&
                java.util.Objects.equals(threats, that.threats) &&
                java.util.Objects.equals(reflectionQuestions, that.reflectionQuestions);
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, responder, reviewer, formDate, trainerFeedback, learningPathway, strengths, weaknesses, opportunities, threats, reflectionQuestions);
    }
}
