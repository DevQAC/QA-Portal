package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseFeedbackMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCohortCourseFeedbacksForTrainerOperation {

    private QaTrainerRepository qaTrainerRepository;

    private CohortCourseFeedbackRepository cohortCourseFeedbackRepository;

    private CohortCourseFeedbackMapper cohortCourseFeedbackMapper;

    public GetCohortCourseFeedbacksForTrainerOperation(QaTrainerRepository qaTrainerRepository,
                                                       CohortCourseFeedbackRepository cohortCourseFeedbackRepository,
                                                       CohortCourseFeedbackMapper cohortCourseFeedbackMapper) {
        this.qaTrainerRepository = qaTrainerRepository;
        this.cohortCourseFeedbackRepository = cohortCourseFeedbackRepository;
        this.cohortCourseFeedbackMapper = cohortCourseFeedbackMapper;
    }

    public List<CohortCourseFeedbackDto> getCohortCourseFeedbacksForTrainer(String trainerUserName) {
        return qaTrainerRepository.findByUserName(trainerUserName).map(t -> getCohortCourseFeedbacks(t))
                .orElseThrow(() -> new QaPortalBusinessException("No record found for trainer"));
    }

    private List<CohortCourseFeedbackDto> getCohortCourseFeedbacks(TrainerEntity trainerEntity) {
        return trainerEntity.getCohorts()
                .stream()
                .flatMap(ce -> ce.getCohortCourses().stream())
                .map(cce -> cohortCourseFeedbackRepository.findByCohortCourse(cce))
                .filter(ccfe -> ccfe.isPresent())
                .map(ccfe -> cohortCourseFeedbackMapper.mapToCohortCourseFeedbackDto(ccfe.get()))
                .collect(Collectors.toList());
    }
}
