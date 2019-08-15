package com.qa.portal.feedback.services.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.dto.FeedbackQuestionCategoryResponseDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseFeedbackEntity;
import com.qa.portal.feedback.persistence.entity.FeedbackQuestionCategoryResponseEntity;

@Component
public class FeedbackMapper {
	
	private final Logger LOGGER = LoggerFactory.getLogger(FeedbackMapper.class);

	private DozerBeanMapper mapper;
	
	private CohortCourseRepository cohortCourseRepo;
	
	private QuestionCategoryRepository questionCategoryRepo;
	
	private QuestionRepository questionRepo;
   
    public FeedbackMapper(DozerBeanMapper mapper, CohortCourseRepository cohortCourseRepo,
			QuestionCategoryRepository questionCategoryRepo, QuestionRepository questionRepo) {
		this.mapper = mapper;
		this.cohortCourseRepo = cohortCourseRepo;
		this.questionCategoryRepo = questionCategoryRepo;
		this.questionRepo = questionRepo;
	}

	public CohortCourseFeedbackEntity mapToFeedbackEntity(CohortCourseFeedbackDto feedbackDto) {
    	CohortCourseFeedbackEntity feedbackEntity = mapper.map(feedbackDto, CohortCourseFeedbackEntity.class);
    	feedbackEntity.setCategoryResponses(createCategoryResponsesEntities(feedbackDto.getCategoryResponses()));
    	feedbackEntity.setCohortCourse(cohortCourseRepo.findById(feedbackDto.getCohortCourse().getId())
    			.orElseThrow(() -> new QaPortalBusinessException("No cohort course found.")));
    	LOGGER.info("feedbackEntity" + feedbackEntity.toString());
    	return feedbackEntity;
    }
    
    private List<FeedbackQuestionCategoryResponseEntity> createCategoryResponsesEntities(List<FeedbackQuestionCategoryResponseDto> feedbackQuestionsDto) {
    	return feedbackQuestionsDto
    			.stream()
    			.map(fq -> createFeedbackQuestionCategoryResponseEntity(fq))
    			.collect(Collectors.toList());
    }
    
    private FeedbackQuestionCategoryResponseEntity createFeedbackQuestionCategoryResponseEntity(FeedbackQuestionCategoryResponseDto feedbackQuestionCategoryResponseDto) {
    	FeedbackQuestionCategoryResponseEntity fqcre = mapper.map(feedbackQuestionCategoryResponseDto, FeedbackQuestionCategoryResponseEntity.class);
    	fqcre.setQuestionCategory(createQuestionCategoryEntity(feedbackQuestionCategoryResponseDto.getQuestionCategory()));
    	return fqcre;
    }
    
    private QuestionCategoryEntity createQuestionCategoryEntity(QuestionCategoryDto questionCategoryDto) {
    	return questionCategoryRepo.findById(questionCategoryDto.getId())
    			.orElseThrow(() -> new QaPortalBusinessException("No question category found for id" + questionCategoryDto.getId()));
    }
    
    public CohortCourseFeedbackDto mapToFeedbackDto(CohortCourseFeedbackEntity feedbackEntity) {
    	CohortCourseFeedbackDto feedbackDto = mapper.map(feedbackEntity, CohortCourseFeedbackDto.class);
    	feedbackDto.setCategoryResponses(createCategoryResponsesEntity(feedbackEntity.getCategoryResponses()));
    	feedbackDto.setCohortCourse(mapper.map(feedbackEntity.getCohortCourse(), CohortCourseDto.class));
    	return feedbackDto;
    }
    
    private List<FeedbackQuestionCategoryResponseDto> createCategoryResponsesEntity(List<FeedbackQuestionCategoryResponseEntity> feedbackQuestionEntity) {
    	return feedbackQuestionEntity.stream().map(fq -> mapper.map(fq, FeedbackQuestionCategoryResponseDto.class))
    			.collect(Collectors.toList());
    }
        
}







