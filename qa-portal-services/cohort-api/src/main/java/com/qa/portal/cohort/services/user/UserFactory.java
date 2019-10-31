package com.qa.portal.cohort.services.user;

import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.dto.TrainerDto;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.persistence.repository.QaUserRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.qa.portal.common.keycloak.KeycloakUserConstants.*;

@Component
public class UserFactory {

    private Map<String, Function<QaUserDetailsDto, QaUserDetailsDto>> factoryMap;

    private QaUserRepository userRepository;

    private QaTrainerRepository trainerRepository;

    private QaTraineeRepository traineeRepository;

    private BaseMapper baseMapper;

    public UserFactory(QaUserRepository userRepository,
                       QaTrainerRepository trainerRepository,
                       QaTraineeRepository traineeRepository,
                       BaseMapper baseMapper) {
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
        this.baseMapper = baseMapper;
    }

    @PostConstruct
    public void init() {
        factoryMap = new HashMap<>();
        factoryMap.put(TRAINEE_USER_ROLE, (userDetailsDto) -> createTrainee(userDetailsDto));
        factoryMap.put(TRAINER_USER_ROLE, (userDetailsDto) -> createTrainer(userDetailsDto));
    }

    public QaUserDetailsDto createUser(QaUserDetailsDto userDetails) {
        return userDetails.getRoleNames().stream()
                .filter(r -> r.contains(TRAINING_ROLE_PREFIX))
                .findFirst()
                .map(r -> factoryMap.get(r))
                .map(e -> e.apply(userDetails))
                .orElseGet(() -> createQaUser(userDetails));
    }

    public QaUserDetailsDto createTrainee(QaUserDetailsDto userDetails) {
        TraineeDto traineeDto = baseMapper.mapObject(userDetails.getUser(), TraineeDto.class);
        TraineeEntity traineeEntity = baseMapper.mapObject(traineeDto, TraineeEntity.class);
        traineeEntity = traineeRepository.save(traineeEntity);
        userDetails.setUser(baseMapper.mapObject(traineeEntity, QaUserDto.class));
        return userDetails;
    }

    public QaUserDetailsDto createTrainer(QaUserDetailsDto userDetails) {
        TrainerDto traineeDto = baseMapper.mapObject(userDetails.getUser(), TrainerDto.class);
        TrainerEntity trainerEntity = baseMapper.mapObject(traineeDto, TrainerEntity.class);
        trainerEntity = trainerRepository.save(trainerEntity);
        userDetails.setUser(baseMapper.mapObject(trainerEntity, QaUserDto.class));
        return userDetails;
    }

    public QaUserDetailsDto createQaUser(QaUserDetailsDto userDetails) {
        QaUserEntity userEntity = baseMapper.mapObject(userDetails.getUser(), QaUserEntity.class);
        userEntity = userRepository.save(userEntity);
        userDetails.setUser(baseMapper.mapObject(userEntity, QaUserDto.class));
        return userDetails;
    }
}
