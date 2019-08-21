package com.qa.portal.feedback.services.mapper;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.TraineeEvaluationSummaryRowDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class TraineeEvaluationSummaryMapper {

    private static final String AWAITING_EVALUATION = "Awaiting";

    private static final String NOT_READY_FOR_EVALUATION = "Locked";

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private BaseMapper baseMapper;

    public TraineeEvaluationSummaryMapper(CohortCourseEvaluationRepository cohortCourseEvaluationRepository,
                                          BaseMapper baseMapper) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
        this.baseMapper = baseMapper;
    }

    public TraineeEvaluationSummaryRowDto mapToEvaluationSummary(CohortCourseEntity cohortCourseEntity,
                                                                 String traineeUserName) {
        TraineeEvaluationSummaryRowDto traineeEvaluationSummaryRowDto = new TraineeEvaluationSummaryRowDto();
        traineeEvaluationSummaryRowDto.setCohortCourseDto(baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class));
        traineeEvaluationSummaryRowDto.setEvaluationStatus(getCohortCourseEvaluationStatus(cohortCourseEntity));
        getCohortCourseEvaluationForTrainee(cohortCourseEntity, traineeUserName)
                .ifPresent(ccee -> setTraineeEvaluationSummary(ccee, traineeEvaluationSummaryRowDto));
        return traineeEvaluationSummaryRowDto;
    }

    private Optional<CohortCourseEvaluationEntity> getCohortCourseEvaluationForTrainee(CohortCourseEntity cohortCourseEntity,
                                                                                       String traineeUserName) {
        return cohortCourseEvaluationRepository.findByCohortCourse(cohortCourseEntity).stream()
                .filter(ccee -> ccee.getTrainee().getUserName().equals(traineeUserName))
                .findFirst();
    }

    private void setTraineeEvaluationSummary(CohortCourseEvaluationEntity cohortCourseEvaluationEntity,
                                             TraineeEvaluationSummaryRowDto traineeEvaluationSummaryRowDto) {
        traineeEvaluationSummaryRowDto.setEvaluationId(cohortCourseEvaluationEntity.getId());
        traineeEvaluationSummaryRowDto.setEvaluationStatus(cohortCourseEvaluationEntity.getStatus());
    }

    private String getCohortCourseEvaluationStatus(CohortCourseEntity cohortCourseEntity) {
        if (cohortCourseEntity.getEndDate().toLocalDate().isBefore(LocalDate.now())) {
            return AWAITING_EVALUATION;
        }
        else {
            return NOT_READY_FOR_EVALUATION;
        }
    }
}
