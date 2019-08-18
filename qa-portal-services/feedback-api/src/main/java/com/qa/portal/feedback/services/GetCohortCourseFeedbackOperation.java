package com.qa.portal.feedback.services;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.dto.FeedbackQuestionCategoryResponseDto;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import com.qa.portal.feedback.services.mapper.CohortCourseEvaluationMapper;
import com.qa.portal.feedback.services.mapper.CohortCourseFeedbackMapper;
import com.qa.portal.feedback.services.mapper.FeedbackQuestionCategoryResponseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.qa.portal.feedback.FeedbackConstants.FEEDBACK_FORM_NAME;

@Component
public class GetCohortCourseFeedbackOperation {
    private CohortCourseRepository cohortCourseRepository;

    private CohortCourseFeedbackRepository cohortCourseFeedbackRepository;

    private FormTypeRepository formTypeRepository;

    private CohortCourseFeedbackMapper cohortCourseFeedbackMapper;

    private FeedbackQuestionCategoryResponseMapper feedbackCategoryQuestionResponseMapper;

    private BaseMapper baseMapper;

    public GetCohortCourseFeedbackOperation(CohortCourseRepository cohortCourseRepository,
                                            CohortCourseFeedbackRepository cohortCourseFeedbackRepository,
                                            FormTypeRepository formTypeRepository,
                                            CohortCourseFeedbackMapper cohortCourseFeedbackMapper,
                                            FeedbackQuestionCategoryResponseMapper feedbackCategoryQuestionResponseMapper,
                                            BaseMapper baseMapper) {
        this.cohortCourseRepository = cohortCourseRepository;
        this.cohortCourseFeedbackRepository = cohortCourseFeedbackRepository;
        this.formTypeRepository = formTypeRepository;
        this.cohortCourseFeedbackMapper = cohortCourseFeedbackMapper;
        this.feedbackCategoryQuestionResponseMapper = feedbackCategoryQuestionResponseMapper;
        this.baseMapper = baseMapper;
    }

    public CohortCourseFeedbackDto getCohortCourseFeedback(Integer cohortCourseId) {
        return cohortCourseRepository.findById(cohortCourseId)
                .map(cce -> getCohortCourseFeedbackForm(cce))
                .orElseThrow(() -> new QaPortalBusinessException("No Cohort Course for the supplied course id"));
    }

    private CohortCourseFeedbackDto getCohortCourseFeedbackForm(CohortCourseEntity cohortCourseEntity) {
        return cohortCourseFeedbackRepository.findByCohortCourse(cohortCourseEntity)
                .map(ccfe -> cohortCourseFeedbackMapper.mapToFeedbackDto(ccfe))
                .orElseGet(() -> getNewCohortCourseFeedbackForm(cohortCourseEntity));
    }

    private CohortCourseFeedbackDto getNewCohortCourseFeedbackForm(CohortCourseEntity cohortCourseEntity) {
        CohortCourseFeedbackDto cohortCourseFeedbackDto = new CohortCourseFeedbackDto();
        cohortCourseFeedbackDto.setCohortCourse(baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class));
        cohortCourseFeedbackDto.setCategoryResponses(getQuestionCategoryResponsesForFormType());
        return cohortCourseFeedbackDto;
    }

    private List<FeedbackQuestionCategoryResponseDto> getQuestionCategoryResponsesForFormType() {
        return formTypeRepository.findByFormName(FEEDBACK_FORM_NAME)
                .map(f -> getQuestionCategoryDtos(f.getQuestionCategories()))
                .orElseThrow(() -> new QaPortalBusinessException("No Questions found for supplied form type " + FEEDBACK_FORM_NAME));
    }

    private List<FeedbackQuestionCategoryResponseDto> getQuestionCategoryDtos(List<QuestionCategoryEntity> questionCategoryEntities) {
        return questionCategoryEntities.stream()
                .map(e -> feedbackCategoryQuestionResponseMapper.createFeedbackQuestionCategoryResponseDto(e))
                .collect(Collectors.toList());
    }
}
