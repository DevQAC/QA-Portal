package com.qa.portal.feedback.dto;

import java.util.List;

public class TraineeEvaluationSummaryDto {
    List<TraineeEvaluationSummaryRowDto> evaluationSummaryRows;

    public List<TraineeEvaluationSummaryRowDto> getEvaluationSummaryRows() {
        return evaluationSummaryRows;
    }

    public void setEvaluationSummaryRows(List<TraineeEvaluationSummaryRowDto> evaluationSummaryRows) {
        this.evaluationSummaryRows = evaluationSummaryRows;
    }
}
