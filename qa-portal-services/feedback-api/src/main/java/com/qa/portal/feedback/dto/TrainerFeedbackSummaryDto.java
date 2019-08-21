package com.qa.portal.feedback.dto;

import java.util.List;

public class TrainerFeedbackSummaryDto {

    private List<TrainerFeedbackSummaryRowDto> feedbackSummaryRowDtos;

    public List<TrainerFeedbackSummaryRowDto> getFeedbackSummaryRowDtos() {
        return feedbackSummaryRowDtos;
    }

    public void setFeedbackSummaryRowDtos(List<TrainerFeedbackSummaryRowDto> feedbackSummaryRowDtos) {
        this.feedbackSummaryRowDtos = feedbackSummaryRowDtos;
    }
}
