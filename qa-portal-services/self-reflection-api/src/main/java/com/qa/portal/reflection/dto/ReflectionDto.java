package com.qa.portal.reflection.dto;

import com.qa.portal.common.dto.QaBaseDto;
import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.dto.TrainerDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ReflectionDto extends QaBaseDto {

    private Integer id;

    private TraineeDto responder;

    private TrainerDto reviewer;

    private LocalDate formDate;

    private String trainerFeedback;

    private String learningPathway;

    private String strengths;

    private String weaknesses;

    private String opportunities;

    private String threats;

    private String status;

    private List<ReflectionQuestionDto> reflectionQuestions;

    public Integer getId() {
        return id;
    }

    public TraineeDto getResponder() {
        return responder;
    }

    public TrainerDto getReviewer() {
        return reviewer;
    }

    public LocalDate getFormDate() {
        return formDate;
    }

    public String getLearningPathway() {
        return learningPathway;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setResponder(TraineeDto responder) {
        this.responder = responder;
    }

    public void setReviewer(TrainerDto reviewer) {
        this.reviewer = reviewer;
    }

    public void setFormDate(LocalDate date) {
        this.formDate = date;
    }

    public void setLearningPathway(String learningPathway) {
        this.learningPathway = learningPathway;
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

    public List<ReflectionQuestionDto> getReflectionQuestions() {
        return reflectionQuestions;
    }

    public void setReflectionQuestions(List<ReflectionQuestionDto> reflectionQuestions) {
        this.reflectionQuestions = reflectionQuestions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReflectionDto that = (ReflectionDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(responder, that.responder) &&
                Objects.equals(reviewer, that.reviewer) &&
                Objects.equals(formDate, that.formDate) &&
                Objects.equals(trainerFeedback, that.trainerFeedback) &&
                Objects.equals(learningPathway, that.learningPathway) &&
                Objects.equals(strengths, that.strengths) &&
                Objects.equals(weaknesses, that.weaknesses) &&
                Objects.equals(opportunities, that.opportunities) &&
                Objects.equals(threats, that.threats) &&
                Objects.equals(status, that.status) &&
                Objects.equals(reflectionQuestions, that.reflectionQuestions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responder, reviewer, formDate, trainerFeedback, learningPathway, strengths, weaknesses, opportunities, threats, status, reflectionQuestions);
    }
}
