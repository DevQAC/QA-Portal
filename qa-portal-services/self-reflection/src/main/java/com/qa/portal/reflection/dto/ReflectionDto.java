package com.qa.portal.reflection.dto;

import com.qa.portal.common.dto.QaBaseDto;
import com.qa.portal.common.dto.QaUserDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;


public final class ReflectionDto extends QaBaseDto {

    private Integer id;

    private QaUserDto responder;

    private QaUserDto reviewer;

    private LocalDate date;

    private String trainerComments;

    private String learningPathway;

    private Set<ReflectionQuestionDto> questions;

    public Integer getId() {
        return id;
    }

    public QaUserDto getResponder() {
        return responder;
    }

    public QaUserDto getReviewer() {
        return reviewer;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTrainerComments() {
        return trainerComments;
    }

    public String getLearningPathway() {
        return learningPathway;
    }

    public Set<ReflectionQuestionDto> getQuestions() {
        return Collections.unmodifiableSet(this.questions);
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTrainerComments(String trainerComments) {
        this.trainerComments = trainerComments;
    }

    public void setLearningPathway(String learningPathway) {
        this.learningPathway = learningPathway;
    }

    public void setQuestions(Set<ReflectionQuestionDto> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReflectionDto that = (ReflectionDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(responder, that.responder) &&
                Objects.equals(reviewer, that.reviewer) &&
                Objects.equals(date, that.date) &&
                Objects.equals(trainerComments, that.trainerComments) &&
                Objects.equals(learningPathway, that.learningPathway) &&
                Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responder, reviewer, date, trainerComments, learningPathway, questions);
    }
}
