package com.qa.portal.reflection.service.mapper;

import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.dto.TrainerDto;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReflectionMapper {

    private DozerBeanMapper mapper;

    private QuestionRepository questionRepository;

    private QaTraineeRepository traineeRepository;

    private ReflectionQuestionMapper reflectionQuestionMapper;

    private Comparator<ReflectionQuestionDto> reflectionQuestionComparator = Comparator.comparingInt(rq -> rq.getQuestion().getId());

    public ReflectionMapper(DozerBeanMapper mapper,
                            QuestionRepository questionRepository,
                            QaTraineeRepository traineeRepository,
                            ReflectionQuestionMapper reflectionQuestionMapper) {
        this.mapper = mapper;
        this.questionRepository = questionRepository;
        this.traineeRepository = traineeRepository;
        this.reflectionQuestionMapper = reflectionQuestionMapper;
    }

    public ReflectionDto mapToReflectionDto(ReflectionEntity reflectionEntity) {
        ReflectionDto reflectionDto = mapper.map(reflectionEntity, ReflectionDto.class);
        reflectionDto.setReflectionQuestions(
                reflectionEntity.getReflectionQuestions()
                        .stream()
                        .map(reflectionQuestionEntity -> createReflectionQuestionDto(reflectionQuestionEntity))
                        .sorted(reflectionQuestionComparator)
                        .collect(Collectors.toList()));
        Optional.ofNullable(reflectionEntity.getResponder())
                .ifPresent(responder -> reflectionDto.setResponder(mapper.map(responder, TraineeDto.class)));
        Optional.ofNullable(reflectionEntity.getReviewer())
                .ifPresent(reviewer -> reflectionDto.setReviewer(mapper.map(reviewer, TrainerDto.class)));
        return reflectionDto;
    }

    public ReflectionEntity mapToReflectionEntity(ReflectionDto dto, String userName) {
        ReflectionEntity reflectionEntity = mapper.map(dto, ReflectionEntity.class);
        reflectionEntity.setReflectionQuestions(dto.getReflectionQuestions().stream()
                .map(reflectionQuestionDto -> createReflectionQuestionEntity(reflectionQuestionDto, reflectionEntity))
                .collect(Collectors.toSet()));
        traineeRepository.findByUserName(userName).ifPresent(traineeEntity -> reflectionEntity.setResponder(traineeEntity));
        return reflectionEntity;
    }

    private ReflectionQuestionEntity createReflectionQuestionEntity(ReflectionQuestionDto reflectionQuestionDto,
                                                                    ReflectionEntity reflectionEntity) {
        ReflectionQuestionEntity entity = new ReflectionQuestionEntity();
        questionRepository.findById(reflectionQuestionDto.getQuestion().getId())
                .ifPresent(questionEntity -> entity.setQuestion(questionEntity));
        entity.setReflection(reflectionEntity);
        entity.setResponse(reflectionQuestionDto.getResponse());
        return entity;
    }

    private ReflectionQuestionDto createReflectionQuestionDto(ReflectionQuestionEntity reflectionQuestionEntity) {
        return reflectionQuestionMapper.mapToReflectionQuestionDto(reflectionQuestionEntity);
    }
}
