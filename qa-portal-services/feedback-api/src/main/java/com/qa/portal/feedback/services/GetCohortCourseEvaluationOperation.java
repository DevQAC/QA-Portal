package com.qa.portal.feedback.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.dto.EvalQuestionCategoryResponseDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
import com.qa.portal.feedback.services.mapper.EvaluationQuestionCategoryResponseMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.qa.portal.feedback.FeedbackConstants.FEEDBACK_FORM_NAME;

@Component
public class GetCohortCourseEvaluationOperation {
    private CohortCourseRepository cohortCourseRepository;

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    private QaTraineeRepository traineeRepository;

    private FormTypeRepository formTypeRepository;

    private CohortCourseEvaluationMapper cohortCourseEvaluationMapper;

    private EvaluationQuestionCategoryResponseMapper evaluationQuestionCategoryResponseMapper;

    private BaseMapper baseMapper;


    // TODO - Generate the constructor

    public CohortCourseEvaluationDto getCohortCourseEvaluation(String traineeUserName) {
        CohortCourseEvaluationDto cohortCourseEvaluationDto = new CohortCourseEvaluationDto();
        cohortCourseEvaluationDto.setCohortCourse(baseMapper.mapObject(getCohortCourseEvaluation(traineeUserName), CohortCourseDto.class));
        // TODO - Add in processing for Category and Question Responses
        return cohortCourseEvaluationDto;
    }

    public CohortCourseEntity getNextCohortCourseForEvaluation(String traineeUserName) {
        TraineeEntity traineeEntity = getTrainee(traineeUserName);
        Optional<LocalDate> nextCohortCourseStartDate = cohortCourseEvaluationRepository.findByTrainee(traineeEntity)
                .stream()
                .sorted((ccee1, ccee2) -> ccee1.getCohortCourse().getEndDate().toLocalDate().isBefore(ccee2.getCohortCourse().getEndDate().toLocalDate()) ? 1 : -1)
                .map(ccee -> ccee.getCohortCourse().getStartDate().toLocalDate().plusDays(7))
                .findFirst();
        return nextCohortCourseStartDate
                .map(sd ->
                        getCohortCourseByStartDate(traineeEntity, nextCohortCourseStartDate.orElseThrow(() -> new QaPortalBusinessException("No Cohort courses to evaluate for trainee"));
    }

    private CohortCourseEntity getCohortCourseByStartDate(TraineeEntity traineeEntity, LocalDate startDate) {
        return traineeEntity.getCohort().getCohortCourses()
                .stream()
                .filter(cc -> cc.getStartDate().equals(startDate))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No Cohort Courses to evaluate for trainee"));
    }

    private TraineeEntity getTrainee(String traineeUserName) {
        return traineeRepository.findByUserName(traineeUserName)
                .orElseThrow(() -> new QaPortalBusinessException("Cannot find trainee"));
    }

    private CohortCourseFeedbackDto getCohortCourseEvaluationForm(CohortCourseEntity cohortCourseEntity) {
        return cohortCourseEvaluationRepository.findByCohortCourse(cohortCourseEntity)
                .map(ccfe -> cohortCourseEvaluationMapper.mapToQaCohortCourseEvaluationDto(ccfe))
                .orElseGet(() -> getNewCohortCourseEvaluationForm(cohortCourseEntity));
    }

    private CohortCourseFeedbackDto getNewCohortCourseEvaluationForm(CohortCourseEntity cohortCourseEntity) {
        CohortCourseFeedbackDto cohortCourseFeedbackDto = new CohortCourseFeedbackDto();
        cohortCourseFeedbackDto.setCohortCourse(baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class));
        cohortCourseFeedbackDto.setCategoryResponses(getQuestionCategoryResponsesForFormType());
        return cohortCourseFeedbackDto;
    }

    private List<EvalQuestionCategoryResponseDto> getQuestionCategoryResponsesForFormType() {
        return formTypeRepository.findByFormName(FEEDBACK_FORM_NAME)
                .map(f -> getQuestionCategoryDtos(f.getQuestionCategories()))
                .orElseThrow(() -> new QaPortalBusinessException("No Questions found for supplied form type " + FEEDBACK_FORM_NAME));
    }

    private List<EvalQuestionCategoryResponseDto> getQuestionCategoryDtos(List<QuestionCategoryEntity> questionCategoryEntities) {
        return questionCategoryEntities.stream()
                .map(e -> evaluationQuestionCategoryResponseMapper.createFeedbackQuestionCategoryResponseDto(e))
                .collect(Collectors.toList());
    }
}
