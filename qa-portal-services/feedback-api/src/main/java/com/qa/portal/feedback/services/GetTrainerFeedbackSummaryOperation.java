package com.qa.portal.feedback.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.feedback.dto.TrainerFeedbackSummaryDto;
import com.qa.portal.feedback.dto.TrainerFeedbackSummaryRowDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseFeedbackEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetTrainerFeedbackSummaryOperation {

    private CohortCourseFeedbackRepository cohortCourseFeedbackRepository;

    private QaTrainerRepository qaTrainerRepository;

    private BaseMapper baseMapper;

    public GetTrainerFeedbackSummaryOperation(CohortCourseFeedbackRepository cohortCourseFeedbackRepository,
                                              QaTrainerRepository qaTrainerRepository,
                                              BaseMapper baseMapper) {
        this.cohortCourseFeedbackRepository = cohortCourseFeedbackRepository;
        this.qaTrainerRepository = qaTrainerRepository;
        this.baseMapper = baseMapper;
    }

    public TrainerFeedbackSummaryDto getTrainerFeedbackSummaryDto(String trainerUserName) {
        TrainerFeedbackSummaryDto trainerFeedbackSummaryDto = new TrainerFeedbackSummaryDto();
        trainerFeedbackSummaryDto.setFeedbackSummaryRowDtos(getTrainerFeedbackSummaryRows(trainerUserName));
        return trainerFeedbackSummaryDto;
    }

    private List<TrainerFeedbackSummaryRowDto> getTrainerFeedbackSummaryRows(String trainerUserName) {
        return qaTrainerRepository.findByUserName(trainerUserName)
                .map(te -> getTrainerFeedbackSummaryRows(te))
                .orElseThrow(() -> new QaPortalBusinessException("No trainer found for supplied user name"));
    }

    private List<TrainerFeedbackSummaryRowDto> getTrainerFeedbackSummaryRows(TrainerEntity trainerEntity) {
        return trainerEntity.getCohorts().stream()
                .flatMap(ce -> ce.getCohortCourses().stream())
                .map(cce -> createTrainerFeedbackSummaryRow(cce))
                .collect(Collectors.toList());
    }

    private TrainerFeedbackSummaryRowDto createTrainerFeedbackSummaryRow(CohortCourseEntity cohortCourseEntity) {
        TrainerFeedbackSummaryRowDto trainerFeedbackSummaryRowDto = new TrainerFeedbackSummaryRowDto();
        trainerFeedbackSummaryRowDto.setCohortCourse(baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class));
        return cohortCourseFeedbackRepository.findByCohortCourse(cohortCourseEntity)
                .map(ccfe -> setTrainerFeedbackSummary(ccfe, trainerFeedbackSummaryRowDto))
                .orElseGet(() -> trainerFeedbackSummaryRowDto);
    }

    private TrainerFeedbackSummaryRowDto setTrainerFeedbackSummary(CohortCourseFeedbackEntity cohortCourseFeedbackEntity,
                                                                   TrainerFeedbackSummaryRowDto trainerFeedbackSummaryRowDto) {
        trainerFeedbackSummaryRowDto.setFeedbackFormId(cohortCourseFeedbackEntity.getId());
        trainerFeedbackSummaryRowDto.setFeedbackStatus(cohortCourseFeedbackEntity.getStatus());
        return trainerFeedbackSummaryRowDto;
    }
}
