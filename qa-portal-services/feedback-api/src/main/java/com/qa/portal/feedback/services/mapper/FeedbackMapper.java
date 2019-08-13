package com.qa.portal.feedback.services.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.dto.FeedbackQuestionCategoryResponseDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseFeedbackEntity;
import com.qa.portal.feedback.persistence.entity.FeedbackQuestionCategoryResponseEntity;

@Component
public class FeedbackMapper {

	private DozerBeanMapper mapper;
	
	private CohortCourseRepository cohortCourseRepo;
	
	public FeedbackMapper(DozerBeanMapper mapper, CohortCourseRepository cohortCourseRepo) {
		this.mapper = mapper;
		this.cohortCourseRepo = cohortCourseRepo;
	}
   
    public CohortCourseFeedbackEntity mapToFeedbackEntity(CohortCourseFeedbackDto feedbackDto) {
    	CohortCourseFeedbackEntity feedbackEntity = mapper.map(feedbackDto, CohortCourseFeedbackEntity.class);
    	feedbackEntity.setCategoryResponses(createCategoryResponsesDto(feedbackDto.getCategoryResponses()));
    	feedbackEntity.setCohortCourse(cohortCourseRepo.findById(feedbackDto.getCohortCourse().getId())
    			.orElseThrow(() -> new QaPortalBusinessException("No cohort course found.")));
    	return feedbackEntity;
    }
    
    private List<FeedbackQuestionCategoryResponseEntity> createCategoryResponsesDto(List<FeedbackQuestionCategoryResponseDto> feedbackQuestionsDto) {
    	return feedbackQuestionsDto.stream().map(fq -> mapper.map(fq, FeedbackQuestionCategoryResponseEntity.class))
    			.collect(Collectors.toList());
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







