package com.qa.portal.reflection.service;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class UpdateSelfReflectionOperation {

    private ReflectionRepository reflectionRepository;

    private ReflectionMapper reflectionMapper;


    @Autowired
    public UpdateSelfReflectionOperation(ReflectionRepository reflectionRepository, ReflectionMapper reflectionMapper) {
        this.reflectionRepository = reflectionRepository;
        this.reflectionMapper = reflectionMapper;
    }

    public ReflectionDto updateSelfReflection(ReflectionDto reflectionDto, String userName) {
        ReflectionEntity reflectionToUpdate = this.reflectionRepository.findById(reflectionDto.getId())
                .orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));

        reflectionToUpdate.setFormDate(Date.valueOf(reflectionDto.getFormDate()));
        //reflectionToUpdate.setResponder(reflectionDto.getResponder());
        //reflectionToUpdate.setReviewer(reflectionDto.getReviewer());
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
                    .filter((rqe) -> rqe
                            .getId()
                            .equals(rq.getId()))
                    .findFirst()
                    .ifPresent((rqe) -> rqe.setResponse(rq.getResponse()));
        });
        return this.reflectionMapper.mapToReflectionDto(this.reflectionRepository.save(reflectionToUpdate));
    }
}
