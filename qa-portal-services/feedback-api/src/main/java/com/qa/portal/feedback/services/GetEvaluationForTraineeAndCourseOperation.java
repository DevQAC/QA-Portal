package com.qa.portal.feedback.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.QuestionCategoryResponseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.service.mapper.QuestionCategoryResponseMapper;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.qa.portal.feedback.FeedbackConstants.EVALUATION_FORM_NAME;

@Component
public class GetEvaluationForTraineeAndCourseOperation {

    private GetCohortCourseEvaluationsForCourseOperation getCohortCourseEvaluationsForCourseOperation;

    private BaseMapper baseMapper;

    private FormTypeRepository formTypeRepository;

    private QuestionCategoryResponseMapper questionCategoryResponseMapper;

    private CohortCourseRepository cohortCourseRepository;

    public GetEvaluationForTraineeAndCourseOperation(GetCohortCourseEvaluationsForCourseOperation getCohortCourseEvaluationsForCourseOperation,
                                                     BaseMapper baseMapper,
                                                     FormTypeRepository formTypeRepository,
                                                     QuestionCategoryResponseMapper questionCategoryResponseMapper,
                                                     CohortCourseRepository cohortCourseRepository) {
        this.getCohortCourseEvaluationsForCourseOperation = getCohortCourseEvaluationsForCourseOperation;
        this.baseMapper = baseMapper;
        this.formTypeRepository = formTypeRepository;
        this.questionCategoryResponseMapper = questionCategoryResponseMapper;
        this.cohortCourseRepository = cohortCourseRepository;
    }

    public CohortCourseEvaluationDto getEvaluationForTraineeAndCourse(String traineeUserName, Integer cohortCourseId) {
        return getCohortCourseEvaluationsForCourseOperation.getAllEvaluationsForCourse(cohortCourseId)
                .stream()
                .filter(eval -> eval.getTrainee().getUserName().equals(traineeUserName))
                .findFirst()
                .orElseGet(() -> getNewCohortCourseEvaluationForm(cohortCourseId));
    }

    private CohortCourseEvaluationDto getNewCohortCourseEvaluationForm(Integer cohortCourseId) {
        return cohortCourseRepository.findById(cohortCourseId)
                .map(cce -> createCohortCourseEvaluationDto(cce) )
                .orElseThrow(() -> new QaPortalBusinessException("No cohort course found for evaluation"));
    }

    private CohortCourseEvaluationDto createCohortCourseEvaluationDto(CohortCourseEntity cohortCourseEntity) {
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
