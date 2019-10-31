package com.qa.portal.feedback.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.QuestionCategoryResponseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.service.mapper.QuestionCategoryResponseMapper;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.qa.portal.feedback.FeedbackConstants.EVALUATION_FORM_NAME;

@Component
public class GetCurrentCohortCourseEvaluationForTraineeOperation {

    private final Logger LOGGER = LoggerFactory.getLogger(GetCurrentCohortCourseEvaluationForTraineeOperation.class);

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private QaTraineeRepository traineeRepository;

    private FormTypeRepository formTypeRepository;

    private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;

    private QuestionCategoryResponseMapper questionCategoryResponseMapper;

    private BaseMapper baseMapper;

    public GetCurrentCohortCourseEvaluationForTraineeOperation(CohortCourseEvaluationRepository cohortCourseEvaluationRepository,
                                                               QaTraineeRepository traineeRepository,
                                                               FormTypeRepository formTypeRepository,
                                                               CohortCourseEvaluationMapper cohortCourseEvaluationMapper,
                                                               QuestionCategoryResponseMapper questionCategoryResponseMapper,
                                                               BaseMapper baseMapper) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
        this.traineeRepository = traineeRepository;
        this.formTypeRepository = formTypeRepository;
        this.cohortCourseEvaluationMapper = cohortCourseEvaluationMapper;
        this.questionCategoryResponseMapper = questionCategoryResponseMapper;
        this.baseMapper = baseMapper;
    }

    public CohortCourseEvaluationDto getCohortCourseEvaluation(String traineeUserName) {
        TraineeEntity traineeEntity = getTrainee(traineeUserName);
        CohortCourseEntity cohortCourseEntity = getNextCohortCourseForEvaluation(traineeEntity);
        CohortCourseEvaluationDto cohortCourseEvaluationDto = getCohortCourseEvaluationForm(cohortCourseEntity, traineeEntity);
        cohortCourseEvaluationDto.setCohortCourse(baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class));
        return cohortCourseEvaluationDto;
    }

    public CohortCourseEntity getNextCohortCourseForEvaluation(TraineeEntity traineeEntity) {
        Optional<LocalDate> nextCohortCourseStartDate = cohortCourseEvaluationRepository.findByTrainee(traineeEntity)
                .stream()
                .sorted((ccee1, ccee2) -> ccee1.getCohortCourse().getEndDate().toLocalDate().isBefore(ccee2.getCohortCourse().getEndDate().toLocalDate()) ? 1 : -1)
                .map(ccee -> ccee.getCohortCourse().getStartDate().toLocalDate().plusDays(7))
                .findFirst();
        return nextCohortCourseStartDate
                .map(sd -> getCohortCourseByStartDate(traineeEntity, nextCohortCourseStartDate))
                .orElseGet(() -> getFirstCohortCourse(traineeEntity));
    }

    private CohortCourseEntity getFirstCohortCourse(TraineeEntity traineeEntity) {
        return traineeEntity.getCohort().getCohortCourses().stream()
                .sorted((cc1, cc2) -> cc1.getStartDate().toLocalDate().isBefore(cc2.getStartDate().toLocalDate()) ? -1 : 1)
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No Cohort courses to evaluate for trainee"));
    }

    private CohortCourseEntity getCohortCourseByStartDate(TraineeEntity traineeEntity, Optional<LocalDate> startDate) {
        return traineeEntity.getCohort().getCohortCourses()
                .stream()
                .filter(cc -> datesMatch(cc.getStartDate(), startDate))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No Cohort Courses to evaluate for trainee"));
    }

    private boolean datesMatch(Date d1, Optional<LocalDate> d2) {
        return d2.map(d -> d1.toLocalDate().equals(d))
                .orElseThrow(() -> new QaPortalBusinessException("No Cohort Courses to evaluate for trainee"));
    }

    private TraineeEntity getTrainee(String traineeUserName) {
        return traineeRepository.findByUserName(traineeUserName)
                .orElseThrow(() -> new QaPortalBusinessException("Cannot find trainee"));
    }

    private CohortCourseEvaluationDto getCohortCourseEvaluationForm(CohortCourseEntity cohortCourseEntity, TraineeEntity traineeEntity) {
        return cohortCourseEvaluationRepository.findByCohortCourse(cohortCourseEntity)
                .stream()
                .filter(ccef -> ccef.getTrainee().getId().equals(traineeEntity.getId()))
                .map(ccef -> cohortCourseEvaluationMapper.mapToCohortCourseEvaluationDto(ccef))
                .findFirst()
                .orElseGet(() -> getNewCohortCourseEvaluationForm(cohortCourseEntity));
    }

    private CohortCourseEvaluationDto getNewCohortCourseEvaluationForm(CohortCourseEntity cohortCourseEntity) {
        CohortCourseEvaluationDto cohortCourseEvaluationDto = new CohortCourseEvaluationDto();
        cohortCourseEvaluationDto.setCohortCourse(baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class));
        cohortCourseEvaluationDto.setCategoryResponses(getQuestionCategoryResponsesForFormType());
        return cohortCourseEvaluationDto;
    }

    private List<QuestionCategoryResponseDto> getQuestionCategoryResponsesForFormType() {
        return formTypeRepository.findByFormName(EVALUATION_FORM_NAME)
                .map(f -> getQuestionCategoryDtos(f.getQuestionCategories()))
                .orElseThrow(() -> new QaPortalBusinessException("No Questions found for supplied form type " + EVALUATION_FORM_NAME));
    }

    private List<QuestionCategoryResponseDto> getQuestionCategoryDtos(List<QuestionCategoryEntity> questionCategoryEntities) {
        return questionCategoryEntities.stream()
                .map(e -> questionCategoryResponseMapper.createQuestionCategoryResponseDto(e))
                .collect(Collectors.toList());
    }
}
