package com.qa.portal.feedback.services.mapper;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseFeedbackEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CohortCourseFeedbackMapper {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CohortCourseFeedbackMapper.class);

	private BaseMapper baseMapper;

	private CohortCourseFeedbackRepository cohortCourseFeedbackRepository;
	
	private CohortCourseRepository cohortCourseRepository;
	
	private QuestionCategoryRepository questionCategoryRepository;
	
	private QuestionRepository questionRepository;

	private FeedbackQuestionCategoryResponseMapper feedbackQuestionCategoryResponseMapper;

	public CohortCourseFeedbackMapper(BaseMapper baseMapper,
									  CohortCourseFeedbackRepository cohortCourseFeedbackRepository,
									  CohortCourseRepository cohortCourseRepository,
									  QuestionCategoryRepository questionCategoryRepository,
									  QuestionRepository questionRepository,
									  FeedbackQuestionCategoryResponseMapper feedbackQuestionCategoryResponseMapper) {
		this.baseMapper = baseMapper;
		this.cohortCourseFeedbackRepository = cohortCourseFeedbackRepository;
		this.cohortCourseRepository = cohortCourseRepository;
		this.questionCategoryRepository = questionCategoryRepository;
		this.questionRepository = questionRepository;
		this.feedbackQuestionCategoryResponseMapper = feedbackQuestionCategoryResponseMapper;
	}

	public CohortCourseFeedbackDto mapToCohortCourseFeedbackDto(CohortCourseFeedbackEntity feedbackEntity) {
		CohortCourseFeedbackDto feedbackDto = baseMapper.mapObject(feedbackEntity, CohortCourseFeedbackDto.class);
		feedbackDto.setCategoryResponses(feedbackDto.getCategoryResponses().stream()
				.collect(Collectors.toList()));
		return feedbackDto;
	}

    public CohortCourseFeedbackEntity createCohortCourseFeedbackEntity(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
    	CohortCourseFeedbackEntity cohortCourseFeedbackEntity = baseMapper.mapObject(cohortCourseFeedbackDto, CohortCourseFeedbackEntity.class);
		cohortCourseFeedbackEntity.setCategoryResponses(
				feedbackQuestionCategoryResponseMapper.createCategoryResponsesEntities(cohortCourseFeedbackDto.getCategoryResponses(), cohortCourseFeedbackEntity));
		cohortCourseFeedbackEntity.setCohortCourse(feedbackQuestionCategoryResponseMapper.getCohortCourseEntity(cohortCourseFeedbackDto.getCohortCourse()));
    	return cohortCourseFeedbackEntity;
	}

	public CohortCourseFeedbackEntity updateCohortCourseFeedback(CohortCourseFeedbackDto cohortCourseFeedbackDto) {
    	CohortCourseFeedbackEntity cohortCourseFeedbackEntity = cohortCourseFeedbackRepository.findById(cohortCourseFeedbackDto.getId())
				.orElseThrow(() -> new QaPortalBusinessException("Cannot find feedback to update"));
    	feedbackQuestionCategoryResponseMapper.setUpdatedCategoryResponses(cohortCourseFeedbackEntity.getCategoryResponses(),
				cohortCourseFeedbackDto.getCategoryResponses());
    	cohortCourseFeedbackEntity.setStatus(cohortCourseFeedbackDto.getStatus());
    	return cohortCourseFeedbackEntity;
	}
}







