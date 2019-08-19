package com.qa.portal.feedback.services.mapper;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseFeedbackDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseFeedbackEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseFeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
    	return cohortCourseFeedbackEntity;
	}

//	private <T extends QuestionCategoryResponseEntity> void setUpdatedCategoryResponses(List<T> questionCategoryResponseEntities, CohortCourseFeedbackDto cohortCourseFeedbackDto) {
//		questionCategoryResponseEntities.stream()
//				.forEach(cr -> setUpdatedCategoryResponse(cr, cohortCourseFeedbackDto.getCategoryResponses()));
//	}

//	private void setUpdatedCategoryResponse(QuestionCategoryResponseEntity questionCategoryResponseEntity, List<QuestionCategoryResponseDto> questionCategoryResponseDtos) {
//		QuestionCategoryResponseDto questionCategoryResponseDto = questionCategoryResponseDtos.stream()
//				.filter(qcrDto -> qcrDto.getId().equals(questionCategoryResponseEntity.getId()))
//				.findFirst()
//				.orElseThrow(() -> new QaPortalBusinessException("Category response not found for feedback"));
//		questionCategoryResponseEntity.getComment().setContent(questionCategoryResponseDto.getComment().getContent());
//		setUpdatedQuestionResponses(questionCategoryResponseEntity, questionCategoryResponseDto);
//	}

//	private void setUpdatedQuestionResponses(QuestionCategoryResponseEntity questionCategoryResponseEntity, QuestionCategoryResponseDto questionCategoryResponseDto) {
//		questionCategoryResponseEntity.getQuestionResponses().stream()
//				.forEach(qre -> setUpdatedQuestionResponse(qre, questionCategoryResponseDto.getQuestionResponses()));
//	}

//	private void setUpdatedQuestionResponse(QuestionResponseEntity questionResponseEntity, List<QuestionResponseDto> questionResponseDtos) {
//		QuestionResponseDto questionResponseDto = questionResponseDtos.stream()
//				.filter(qrDto -> qrDto.getQuestion().getId().equals(questionResponseEntity.getQuestion().getId()))
//				.findFirst()
//				.orElseThrow(() -> new QaPortalBusinessException("Cannot find question in question response of feedback"));
//		questionResponseEntity.setResponseValues(questionResponseDto.getResponseValues());
//		questionResponseEntity.getComment().setContent(questionResponseDto.getComment().getContent());
//	}

    
//    private List<FeedbackQuestionCategoryResponseEntity> createCategoryResponsesEntities(List<QuestionCategoryResponseDto> questionCategoryResponseDtos,
//																						 CohortCourseFeedbackEntity cohortCourseFeedbackEntity) {
//    	return questionCategoryResponseDtos
//    			.stream()
//    			.map(fq -> createFeedbackQuestionCategoryResponseEntity(fq, cohortCourseFeedbackEntity))
//    			.collect(Collectors.toList());
//    }
    
//    private FeedbackQuestionCategoryResponseEntity createFeedbackQuestionCategoryResponseEntity(QuestionCategoryResponseDto questionCategoryResponseDto,
//																								CohortCourseFeedbackEntity cohortCourseFeedbackEntity) {
//    	FeedbackQuestionCategoryResponseEntity fqcre = baseMapper.mapObject(questionCategoryResponseDto, FeedbackQuestionCategoryResponseEntity.class);
//    	fqcre.setQuestionCategory(getQuestionCategoryEntity(questionCategoryResponseDto.getQuestionCategory()));
//    	fqcre.setCourseFeedback(cohortCourseFeedbackEntity);
//    	return fqcre;
//    }
    
//    private List<FeedbackQuestionCategoryResponseEntity> createQuestionCategoryResponsesEntity(List<QuestionCategoryResponseDto> questionCategoryResponseDtos) {
//    	return questionCategoryResponseDtos.stream().map(fq -> baseMapper.mapObject(fq, FeedbackQuestionCategoryResponseEntity.class))
//    			.collect(Collectors.toList());
//    }

//    private FeedbackQuestionCategoryResponseEntity createFeedbackQuestionResponseCategory(QuestionCategoryResponseDto questionCategoryResponseDto) {
//    	FeedbackQuestionCategoryResponseEntity feedbackQuestionCategoryResponseEntity = new FeedbackQuestionCategoryResponseEntity();
//    	feedbackQuestionCategoryResponseEntity.setQuestionCategory(getQuestionCategoryEntity(questionCategoryResponseDto.getQuestionCategory()));
//    	feedbackQuestionCategoryResponseEntity.setQuestionResponses(createQuestionResponseEntities(questionCategoryResponseDto.getQuestionResponses(),
//				feedbackQuestionCategoryResponseEntity));
//    	return feedbackQuestionCategoryResponseEntity;
//	}

//	private QuestionCategoryEntity getQuestionCategoryEntity(QuestionCategoryDto questionCategoryDto) {
//		return questionCategoryRepository.findById(questionCategoryDto.getId())
//				.orElseThrow(() -> new QaPortalBusinessException("No question category found for id" + questionCategoryDto.getId()));
//	}

//    private List<QuestionResponseEntity> createQuestionResponseEntities(List<QuestionResponseDto> questionResponseDtos, QuestionCategoryResponseEntity questionCategoryResponseEntity) {
//    		return questionResponseDtos.stream()
//					.map(qr -> createQuestionResponseEntity(qr, questionCategoryResponseEntity))
//					.collect(Collectors.toList());
//	}

//	private QuestionResponseEntity createQuestionResponseEntity(QuestionResponseDto questionResponseDto, QuestionCategoryResponseEntity questionCategoryResponseEntity) {
//    	QuestionResponseEntity questionResponseEntity = new QuestionResponseEntity();
//    	questionResponseEntity.setCategoryResponse(questionCategoryResponseEntity);
//    	questionResponseEntity.setQuestion(getQuestionEntity(questionResponseDto.getQuestion().getId()));
//    	questionResponseEntity.setResponseValues(questionResponseDto.getResponseValues());
//    	return questionResponseEntity;
//	}

//	private QuestionEntity getQuestionEntity(Integer questionId) {
//    	return questionRepository.findById(questionId).orElseThrow(() -> new QaPortalBusinessException("Cannot find question for feedback form"));
//	}
}







