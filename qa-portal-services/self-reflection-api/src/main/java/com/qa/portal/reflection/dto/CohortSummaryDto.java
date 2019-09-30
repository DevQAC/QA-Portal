package com.qa.portal.reflection.dto;

import java.util.List;
import java.util.Objects;

import com.qa.portal.common.dto.QaBaseDto;

public class CohortSummaryDto extends QaBaseDto {

    private String cohortName;

    private List<Double> averageRatings;

    private List<ReflectionQuestionDto> scores;

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    public List<ReflectionQuestionDto> getScores() {
        return scores;
    }

    public void setScores(List<ReflectionQuestionDto> scores) {
        this.scores = scores;
    }

    public List<Double> getAverageRatings() {
        return averageRatings;
    }

    public void setAverageRatings(List<Double> averageRatings) {
        this.averageRatings = averageRatings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CohortSummaryDto that = (CohortSummaryDto) o;
        return Objects.equals(cohortName, that.cohortName) &&
                Objects.equals(averageRatings, that.averageRatings) &&
                Objects.equals(scores, that.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cohortName, averageRatings, scores);
    }
}
