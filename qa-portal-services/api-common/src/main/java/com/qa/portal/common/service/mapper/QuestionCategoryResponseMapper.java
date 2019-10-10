package com.qa.portal.common.service.mapper;

import com.qa.portal.common.dto.*;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.*;
import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class QuestionCategoryResponseMapper<S extends QuestionCategoryResponseEntity, T> {

    private final Logger LOGGER = LoggerFactory.getLogger(QuestionCategoryResponseMapper.class);

    private BaseMapper baseMapper;

    private CohortCourseRepository cohortCourseRepository;

    private QuestionCategoryRepository questionCategoryRepository;

    private QuestionRepository questionRepository;

    public QuestionCategoryResponseMapper(BaseMapper baseMapper,
                                          CohortCourseRepository cohortCourseRepository,
                                          QuestionCategoryRepository questionCategoryRepository,
                                          QuestionRepository questionRepository) {
        this.baseMapper = baseMapper;
        this.cohortCourseRepository = cohortCourseRepository;
        this.questionCategoryRepository = questionCategoryRepository;
        this.questionRepository = questionRepository;
    }

    public QuestionCategoryResponseDto createQuestionCategoryResponseDto(QuestionCategoryEntity questionCategoryEntity) {
        QuestionCategoryResponseDto questionCategoryResponseDto = new QuestionCategoryResponseDto();
        QuestionCategoryDto questionCategoryDto = baseMapper.mapObject(questionCategoryEntity, QuestionCategoryDto.class);
        questionCategoryResponseDto.setQuestionCategory(questionCategoryDto);
        questionCategoryResponseDto.setQuestionResponses(createQuestionResponseDtos(questionCategoryEntity));
        return questionCategoryResponseDto;
    }

    public void setUpdatedCategoryResponses(List<S> questionCategoryResponseEntities,
                                            List<QuestionCategoryResponseDto> questionCategoryResponseDtos) {
        questionCategoryResponseEntities.stream()
                .forEach(cr -> setUpdatedCategoryResponse(cr, questionCategoryResponseDtos));
    }

    public void setUpdatedCategoryResponse(QuestionCategoryResponseEntity questionCategoryResponseEntity,
                                           List<QuestionCategoryResponseDto> questionCategoryResponseDtos) {
        QuestionCategoryResponseDto questionCategoryResponseDto = questionCategoryResponseDtos.stream()
                .filter(qcrDto -> qcrDto.getId().equals(questionCategoryResponseEntity.getId()))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("Category response not found for feedback"));
        updateCategoryResponseComment(questionCategoryResponseEntity, questionCategoryResponseDto);
        setUpdatedQuestionResponses(questionCategoryResponseEntity, questionCategoryResponseDto);
    }

    public CohortCourseEntity getCohortCourseEntity(CohortCourseDto cohortCourseDto) {
        return cohortCourseRepository.findById(cohortCourseDto.getId()).orElseThrow(() -> new QaPortalBusinessException("Cannot find cohort course for feedback"));
    }

    private void updateCategoryResponseComment(QuestionCategoryResponseEntity questionCategoryResponseEntity,
                                               QuestionCategoryResponseDto questionCategoryResponseDto) {
        Optional.ofNullable(questionCategoryResponseDto.getComment())
                .ifPresent(comment -> setCommentOnCategoryResponseEntity(comment.getContent(), questionCategoryResponseEntity));
    }

    private void setCommentOnCategoryResponseEntity(String content, QuestionCategoryResponseEntity questionCategoryResponseEntity) {
        CommentEntity commentEntity = Optional.ofNullable(questionCategoryResponseEntity.getComment())
                .orElseGet(() -> new CommentEntity());
        commentEntity.setContent(content);
        questionCategoryResponseEntity.setComment(commentEntity);
    }

    private List<QuestionResponseDto> createQuestionResponseDtos(QuestionCategoryEntity questionCategoryEntity) {
        return questionCategoryEntity.getQuestions().stream()
                .map(qe -> createQuestionResponseDto(qe))
                .collect(Collectors.toList());
    }

    private QuestionResponseDto createQuestionResponseDto(QuestionEntity questionEntity) {
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setQuestion(baseMapper.mapObject(questionEntity, QuestionDto.class));
        questionResponseDto.setResponseValues(new ArrayList<>());
        return questionResponseDto;
    }

    private void setUpdatedQuestionResponses(QuestionCategoryResponseEntity<T> questionCategoryResponseEntity,
                                             QuestionCategoryResponseDto questionCategoryResponseDto) {
        questionCategoryResponseEntity.getQuestionResponses().stream()
                .forEach(qre -> setUpdatedQuestionResponse(qre, questionCategoryResponseDto.getQuestionResponses()));
    }

    private void setUpdatedQuestionResponse(QuestionResponseEntity questionResponseEntity,
                                            List<QuestionResponseDto> questionResponseDtos) {
        QuestionResponseDto questionResponseDto = questionResponseDtos.stream()
                .filter(qrDto -> qrDto.getQuestion().getId().equals(questionResponseEntity.getQuestion().getId()))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("Cannot find question in question response of feedback"));
        questionResponseEntity.setResponseValues(getQuestionResponseValue(questionResponseDto));
        updateQuestionResponseComment(questionResponseEntity, questionResponseDto);
    }

    private void updateQuestionResponseComment(QuestionResponseEntity questionResponseEntity,
                                               QuestionResponseDto questionResponseDto) {
        Optional.ofNullable(questionResponseDto.getComment())
                .ifPresent(comment -> setCommentOnCategoryResponseEntity(comment.getContent(), questionResponseEntity));
    }

    private void setCommentOnCategoryResponseEntity(String content, QuestionResponseEntity questionResponseEntity) {
        CommentEntity commentEntity = Optional.ofNullable(questionResponseEntity.getComment())
                .orElseGet(() -> new CommentEntity());
        commentEntity.setContent(content);
        questionResponseEntity.setComment(commentEntity);
    }

    public List<S> createCategoryResponsesEntities(List<QuestionCategoryResponseDto> questionCategoryResponseDtos,
                                                   T categoryResponseParent) {
        return questionCategoryResponseDtos
                .stream()
                .map(fq -> createFeedbackQuestionCategoryResponseEntity(fq, categoryResponseParent))
                .collect(Collectors.toList());
    }

    private S createFeedbackQuestionCategoryResponseEntity(QuestionCategoryResponseDto questionCategoryResponseDto,
                                                           T categoryResponseParent) {
        Class<S> categoryResponseClass = (Class<S>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        S fqcre = baseMapper.mapObject(questionCategoryResponseDto, categoryResponseClass);
        fqcre.setQuestionCategory(getQuestionCategoryEntity(questionCategoryResponseDto.getQuestionCategory()));
        fqcre.setQuestionResponses(createQuestionResponseEntities(questionCategoryResponseDto.getQuestionResponses(), fqcre));
        fqcre.setParent(categoryResponseParent);
        if (questionCategoryResponseDto.getComment() != null) {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setContent(questionCategoryResponseDto.getComment().getContent());
            fqcre.setComment(commentEntity);
        }
        return fqcre;
    }

    private QuestionCategoryEntity getQuestionCategoryEntity(QuestionCategoryDto questionCategoryDto) {
        return questionCategoryRepository.findById(questionCategoryDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("No question category found for id" + questionCategoryDto.getId()));
    }

    private List<QuestionResponseEntity> createQuestionResponseEntities(List<QuestionResponseDto> questionResponseDtos, QuestionCategoryResponseEntity questionCategoryResponseEntity) {
        return questionResponseDtos.stream()
                .map(qr -> createQuestionResponseEntity(qr, questionCategoryResponseEntity))
                .collect(Collectors.toList());
    }

    private QuestionResponseEntity createQuestionResponseEntity(QuestionResponseDto questionResponseDto, QuestionCategoryResponseEntity questionCategoryResponseEntity) {
        QuestionResponseEntity questionResponseEntity = new QuestionResponseEntity();
        questionResponseEntity.setCategoryResponse(questionCategoryResponseEntity);
        questionResponseEntity.setQuestion(getQuestionEntity(questionResponseDto.getQuestion().getId()));
        questionResponseEntity.setResponseValues(getQuestionResponseValue(questionResponseDto));
        if (questionResponseDto.getComment() != null) {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setContent(questionResponseDto.getComment().getContent());
            questionResponseEntity.setComment(commentEntity);
        }
        return questionResponseEntity;
    }

    private String getQuestionResponseValue(QuestionResponseDto questionResponseDto) {
        // Converts the List<String> response Value in Dto to String value to store on the entity
        return baseMapper.mapObject(questionResponseDto, QuestionResponseEntity.class).getResponseValues();
    }

    private QuestionEntity getQuestionEntity(Integer questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new QaPortalBusinessException("Cannot find question for feedback form"));
    }
}
