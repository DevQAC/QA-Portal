package com.qa.portal.reflection.service;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
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

    private QaTraineeRepository traineeRepository;

    @Autowired
    public GetSelfReflectionOperation(ReflectionRepository reflectionRepository,
                                      ReflectionMapper reflectionMapper,
                                      QaTrainerRepository trainerRepository,
                                      QaTraineeRepository traineeRepository) {
        this.reflectionRepository = reflectionRepository;
        this.reflectionMapper = reflectionMapper;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
    }

    public ReflectionDto getSelfReflectionById(Integer id) {
        ReflectionEntity reflection = this.reflectionRepository.findById(id)
                .orElseThrow(() -> new QaResourceNotFoundException("No Reflection found for supplied id"));
        return this.reflectionMapper.mapToReflectionDto(reflection);
    }

    public ReflectionDto getSelfReflectionByUserAndStatus(String userName, String status){
        TraineeEntity trainee = this.traineeRepository.findByUserName(userName)
                .orElseThrow(() -> new QaResourceNotFoundException("No Trainee found for supplied user name"));
        return this.reflectionRepository.findByResponderAndStatus(trainee, status)
                .map(entity -> this.reflectionMapper.mapToReflectionDto(entity))
                .orElseGet(ReflectionDto::new);
    }

    public ReflectionDto getSelfReflectionByUserAndDate(String userName, LocalDate date) {
        TrainerEntity trainer = this.trainerRepository.findByUserName(userName)
                .orElseThrow(() -> new QaResourceNotFoundException("No Trainer found for supplied user name"));
        return this.reflectionRepository.findByReviewerAndFormDate(trainer, date)
                .map(entity -> this.reflectionMapper.mapToReflectionDto(entity))
                .orElseGet(ReflectionDto::new);
    }
}
