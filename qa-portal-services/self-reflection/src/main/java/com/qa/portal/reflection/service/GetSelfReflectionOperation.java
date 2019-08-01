package com.qa.portal.reflection.service;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GetSelfReflectionOperation {

    private ReflectionRepository reflectionRepository;

    private ReflectionMapper reflectionMapper;

    private QaTrainerRepository trainerRepository;

    @Autowired
    public GetSelfReflectionOperation(ReflectionRepository reflectionRepository,
                                      ReflectionMapper reflectionMapper,
                                      QaTrainerRepository trainerRepository) {
        this.reflectionRepository = reflectionRepository;
        this.reflectionMapper = reflectionMapper;
        this.trainerRepository = trainerRepository;
    }

    public ReflectionDto getSelfReflectionById(Integer id) {
        ReflectionEntity reflection = this.reflectionRepository.findById(id)
                .orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
        return this.reflectionMapper.mapToReflectionDto(reflection);
    }

    public ReflectionDto getSelfReflectionByUserAndDate(String userName, LocalDate date) {
        TrainerEntity trainer = this.trainerRepository.findByUserName(userName)
                .orElseThrow(() -> new QaResourceNotFoundException("Trainer does not exist"));
        return this.reflectionRepository.findByReviewerAndFormDate(trainer, date)
                .map(entity -> this.reflectionMapper.mapToReflectionDto(entity))
                .orElseGet(ReflectionDto::new);
    }
}