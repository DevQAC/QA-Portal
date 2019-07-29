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

    private String trainerComments;

    private String learningPathway;

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

    public String getTrainerComments() {
        return trainerComments;
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

    public void setTrainerComments(String trainerComments) {
        this.trainerComments = trainerComments;
    }

    public void setLearningPathway(String learningPathway) {
        this.learningPathway = learningPathway;
    }

    public void setQuestions(Set<ReflectionQuestionDto> questions) {
        this.reflectionQuestions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ReflectionDto that = (ReflectionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(responder, that.responder)
                && Objects.equals(reviewer, that.reviewer) && Objects.equals(formDate, that.formDate)
                && Objects.equals(trainerComments, that.trainerComments)
                && Objects.equals(learningPathway, that.learningPathway) && Objects.equals(reflectionQuestions, that.reflectionQuestions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, responder, reviewer, formDate, trainerComments, learningPathway, reflectionQuestions);
    }
}
