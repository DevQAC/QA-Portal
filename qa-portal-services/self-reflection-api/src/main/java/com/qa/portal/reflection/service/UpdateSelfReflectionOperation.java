package com.qa.portal.reflection.service;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class UpdateSelfReflectionOperation {

    private ReflectionRepository reflectionRepository;

    private ReflectionMapper reflectionMapper;


    public UpdateSelfReflectionOperation(ReflectionRepository reflectionRepository, ReflectionMapper reflectionMapper) {
        this.reflectionRepository = reflectionRepository;
        this.reflectionMapper = reflectionMapper;
    }

    public ReflectionDto updateSelfReflection(ReflectionDto reflectionDto, String userName) {
        ReflectionEntity reflectionToUpdate = this.reflectionRepository.findById(reflectionDto.getId())
                .orElseThrow(() -> new QaResourceNotFoundException("No Reflection form found for supplied id"));
        reflectionToUpdate.setFormDate(Date.valueOf(reflectionDto.getFormDate()));
        reflectionToUpdate.setTrainerFeedback(reflectionDto.getTrainerFeedback());
        reflectionToUpdate.setLearningPathway(reflectionDto.getLearningPathway());
        reflectionToUpdate.setStrengths(reflectionDto.getStrengths());
        reflectionToUpdate.setWeaknesses(reflectionDto.getWeaknesses());
        reflectionToUpdate.setOpportunities(reflectionDto.getOpportunities());
        reflectionToUpdate.setThreats(reflectionDto.getThreats());
        reflectionToUpdate.setStatus(reflectionDto.getStatus());
        reflectionDto.getReflectionQuestions().stream().forEach((rq) -> {
            reflectionToUpdate.getReflectionQuestions()
                    .stream()
                    .filter((rqe) -> rqe.getId().equals(rq.getId()))
                    .findFirst()
                    .ifPresent((rqe) -> updateResponses(rqe, rq));
        });
        return this.reflectionMapper.mapToReflectionDto(this.reflectionRepository.save(reflectionToUpdate));
    }

    private void updateResponses(ReflectionQuestionEntity reflectionQuestionEntity, ReflectionQuestionDto reflectionQuestionDto) {
        reflectionQuestionEntity.setResponse(reflectionQuestionDto.getResponse());
        reflectionQuestionEntity.setTrainerResponse(reflectionQuestionDto.getTrainerResponse());
    }
}
