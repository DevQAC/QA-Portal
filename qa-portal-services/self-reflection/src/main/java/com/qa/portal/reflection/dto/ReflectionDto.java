package com.qa.portal.reflection.dto;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qa.portal.common.dto.QaBaseDto;
import com.qa.portal.common.dto.QaTraineeDto;
import com.qa.portal.common.dto.QaTrainerDto;

public final class ReflectionDto extends QaBaseDto {

    private Integer id;

    private QaTraineeDto responder;

    private QaTrainerDto reviewer;

    private String trainerComments;

    private String learningPathway;

    private Date date;

    private Set<QuestionDto> questions;

    // @JsonCreator
    // public ReflectionDto(@JsonProperty Integer id, @JsonProperty QaTraineeDto
    // responder, @JsonProperty QaTrainerDto reviewer,
    // @JsonProperty Date date, @JsonProperty Set<QuestionDto> questions) {
    // super();
    // this.id = id;
    // this.responder = responder;
    // this.reviewer = reviewer;
    // this.date = date;
    // this.setQuestions(questions);
    // }

    // public ReflectionDto() {
    // this.setQuestions(null);
    // }

    public Integer getId() {
        return id;
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

    public QaTraineeDto getResponder() {
        return responder;
    }

    public QaTrainerDto getReviewer() {
        return reviewer;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setResponder(QaTraineeDto responder) {
        this.responder = responder;
    }

    public void setReviewer(QaTrainerDto reviewer) {
        this.reviewer = reviewer;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setQuestions(Set<QuestionDto> questions) {
        this.questions = Optional.ofNullable(questions).orElse(new HashSet<QuestionDto>());
    }

    public void setTrainerComments(String trainerComments) {
        this.trainerComments = trainerComments;
    }

    public void setLearningPathway(String learningPathway) {
        this.learningPathway = learningPathway;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ReflectionDto that = (ReflectionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(responder, that.responder)
                && Objects.equals(reviewer, that.reviewer) && Objects.equals(date, that.date)
                && Objects.equals(trainerComments, that.trainerComments)
                && Objects.equals(learningPathway, that.learningPathway) && Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responder, reviewer, date, trainerComments, learningPathway, questions);
    }
}
