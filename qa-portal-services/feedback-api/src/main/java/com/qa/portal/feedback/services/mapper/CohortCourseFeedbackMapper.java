package com.qa.portal.feedback.services.mapper;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.dto.FeedbackQuestionCategoryResponseDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseFeedbackEntity;
import com.qa.portal.feedback.persistence.entity.FeedbackQuestionCategoryResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CohortCourseEvaluationMapper {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CohortCourseEvaluationMapper.class);

	private BaseMapper baseMapper;
	
	private CohortCourseRepository cohortCourseRepo;
	
	private QuestionCategoryRepository questionCategoryRepo;
	
	private QuestionRepository questionRepo;
   
    public CohortCourseFeedbackMapper(BaseMapper baseMapper,
									  CohortCourseRepository cohortCourseRepo,
									  QuestionCategoryRepository questionCategoryRepo,
									  QuestionRepository questionRepo) {
		this.baseMapper = baseMapper;
		this.cohortCourseRepo = cohortCourseRepo;
		this.questionCategoryRepo = questionCategoryRepo;
		this.questionRepo = questionRepo;
	}

	public CohortCourseFeedbackDto mapToCohortCourseFeedbackDto(CohortCourseFeedbackEntity cohortCourseFeedbackEntity) {
		return baseMapper.mapObject(cohortCourseFeedbackEntity, CohortCourseFeedbackDto.class);
	}

	public CohortCourseFeedbackEntity mapToCohortCourseFeedbackEntity(CohortCourseFeedbackDto feedbackDto) {
    	CohortCourseFeedbackEntity feedbackEntity = baseMapper.mapObject(feedbackDto, CohortCourseFeedbackEntity.class);
    	feedbackEntity.setCategoryResponses(createCategoryResponsesEntities(feedbackDto.getCategoryResponses()));
    	feedbackEntity.setCohortCourse(cohortCourseRepo.findById(feedbackDto.getCohortCourse().getId())
    			.orElseThrow(() -> new QaPortalBusinessException("No cohort course found.")));
    	return feedbackEntity;
    }
    
    private List<FeedbackQuestionCategoryResponseEntity> createCategoryResponsesEntities(List<FeedbackQuestionCategoryResponseDto> feedbackQuestionsDto) {
    	return feedbackQuestionsDto
    			.stream()
    			.map(fq -> createFeedbackQuestionCategoryResponseEntity(fq))
    			.collect(Collectors.toList());
    }
    
    private FeedbackQuestionCategoryResponseEntity createFeedbackQuestionCategoryResponseEntity(FeedbackQuestionCategoryResponseDto feedbackQuestionCategoryResponseDto) {
    	FeedbackQuestionCategoryResponseEntity fqcre = baseMapper.mapObject(feedbackQuestionCategoryResponseDto, FeedbackQuestionCategoryResponseEntity.class);
    	fqcre.setQuestionCategory(createQuestionCategoryEntity(feedbackQuestionCategoryResponseDto.getQuestionCategory()));
    	return fqcre;
    }
    
    private QuestionCategoryEntity createQuestionCategoryEntity(QuestionCategoryDto questionCategoryDto) {
    	return questionCategoryRepo.findById(questionCategoryDto.getId())
    			.orElseThrow(() -> new QaPortalBusinessException("No question category found for id" + questionCategoryDto.getId()));
    }
    
    public CohortCourseFeedbackDto mapToFeedbackDto(CohortCourseFeedbackEntity feedbackEntity) {
    	CohortCourseFeedbackDto feedbackDto = baseMapper.mapObject(feedbackEntity, CohortCourseFeedbackDto.class);
    	feedbackDto.setCategoryResponses(createCategoryResponsesEntity(feedbackEntity.getCategoryResponses()));
    	feedbackDto.setCohortCourse(baseMapper.mapObject(feedbackEntity.getCohortCourse(), CohortCourseDto.class));
    	return feedbackDto;
    }
    
    private List<FeedbackQuestionCategoryResponseDto> createCategoryResponsesEntity(List<FeedbackQuestionCategoryResponseEntity> feedbackQuestionEntity) {
    	return feedbackQuestionEntity.stream().map(fq -> baseMapper.mapObject(fq, FeedbackQuestionCategoryResponseDto.class))
    			.collect(Collectors.toList());
    }
}







