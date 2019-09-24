package com.qa.portal.feedback.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.dto.TrainerCourseEvaluationSummaryDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GetCohortCourseEvaluationsForCourseOperation {

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;

    private CohortCourseRepository cohortCourseRepository;

    private EvaluationRatingsCalculator evaluationRatingsCalculator;

    public GetCohortCourseEvaluationsForCourseOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository,
                                                        CohortCourseEvaluationMapper cohortCourseEvaluationMapper,
                                                        CohortCourseRepository cohortCourseRepository,
                                                        EvaluationRatingsCalculator evaluationRatingsCalculator) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
        this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
        this.cohortCourseRepository = cohortCourseRepository;
        this.evaluationRatingsCalculator = evaluationRatingsCalculator;
    }

    public TrainerCourseEvaluationSummaryDto getEvaluationSummaryForCourse(Integer cohortCourseId) {
        TrainerCourseEvaluationSummaryDto trainerCourseEvaluationSummaryDto = new TrainerCourseEvaluationSummaryDto();
        trainerCourseEvaluationSummaryDto.setTraineeEvaluationsForCourse(getSubmittedEvaluationsForCourse(cohortCourseId));
        getCohortCourseEntity(cohortCourseId)
                .ifPresent(cce -> {
                    setTqiForCohortCourse(getSubmittedCohortCourseEvaluations(cce), trainerCourseEvaluationSummaryDto);
                    setTqiForCourseHistory(cohortCourseRepository.findByCourse(cce.getCourse()),
                            trainerCourseEvaluationSummaryDto);
                });
        return trainerCourseEvaluationSummaryDto;
    }

    public List<CohortCourseEvaluationDto> getAllEvaluationsForCourse(Integer cohortCourseId) {
        return getCohortCourseEntity(cohortCourseId)
                .map(cce -> getAllEvaluationsForCourse(cce))
                .orElseThrow(() -> new QaPortalBusinessException("Cohort course not found"));
    }

    public List<CohortCourseEvaluationDto> getSubmittedEvaluationsForCourse(Integer cohortCourseId) {
        return getCohortCourseEntity(cohortCourseId)
                .map(cce -> getSubmittedEvaluationsForCourse(cce))
                .orElseThrow(() -> new QaPortalBusinessException("Cohort course not found"));
    }

    private List<CohortCourseEvaluationDto> getAllEvaluationsForCourse(CohortCourseEntity cohortCourseEntity) {
        return getAllCohortCourseEvaluations(cohortCourseEntity)
                .stream()
                .map(e -> cohortCourseEvaluationMapper.mapToCohortCourseEvaluationDto(e))
                .collect(Collectors.toList());
    }

    private List<CohortCourseEvaluationDto> getSubmittedEvaluationsForCourse(CohortCourseEntity cohortCourseEntity) {
        return getSubmittedCohortCourseEvaluations(cohortCourseEntity)
                .stream()
                .map(e -> cohortCourseEvaluationMapper.mapToCohortCourseEvaluationDto(e))
                .collect(Collectors.toList());
    }

    private Optional<CohortCourseEntity> getCohortCourseEntity(Integer cohortCourseId) {
        return cohortCourseRepository.findById(cohortCourseId);
    }

    private List<CohortCourseEvaluationEntity> getAllCohortCourseEvaluations(CohortCourseEntity cohortCourseEntity) {
        return this.cohortCourseEvaluationRepository.findByCohortCourse(cohortCourseEntity)
                .stream()
                .collect(Collectors.toList());
    }

    private List<CohortCourseEvaluationEntity> getSubmittedCohortCourseEvaluations(CohortCourseEntity cohortCourseEntity) {
        return this.cohortCourseEvaluationRepository.findByCohortCourse(cohortCourseEntity)
                .stream()
                .filter(cce -> cce.getStatus().equals("Submitted"))
                .collect(Collectors.toList());
    }

    private void setTqiForCohortCourse(List<CohortCourseEvaluationEntity> evaluations,
                                       TrainerCourseEvaluationSummaryDto trainerCourseEvaluationSummaryDto) {
        evaluationRatingsCalculator.getTqiRatingForCourse(evaluations)
                .ifPresent(v -> trainerCourseEvaluationSummaryDto
                        .setCourseTqi(evaluationRatingsCalculator.roundToTwoDecimalPlaces(new BigDecimal(v)).toString()));
    }

    private void setTqiForCourseHistory(List<CohortCourseEntity> courseEntity,
                                        TrainerCourseEvaluationSummaryDto trainerCourseEvaluationSummaryDto) {
        evaluationRatingsCalculator.getTqiRatingForCourseHistory(courseEntity)
                .ifPresent(v -> trainerCourseEvaluationSummaryDto
                .setCourseHistoryTqi(evaluationRatingsCalculator.roundToTwoDecimalPlaces(new BigDecimal(v)).toString()));
    }
}
