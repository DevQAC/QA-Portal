package com.qa.portal.common.util.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.dto.TrainerDto;
import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;

@Component
@Primary
public class BaseMapper {

    private DozerBeanMapper mapper;

    public BaseMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    public DozerBeanMapper getMapper() {
        return mapper;
    }

    public <S, T> T mapObject(S object, Class<T> clazz) {
        return mapper.map(object, clazz);
    }

    public QaUserEntity mapToQaUserEntity(QaUserDto qaUserDto) {
        return mapper.map(qaUserDto, QaUserEntity.class);
    }

    public QaUserDto mapToQaUserDto(QaUserEntity qaUserEntity) {
        return mapper.map(qaUserEntity, QaUserDto.class);
    }
//
//    public TraineeEntity mapToQaTraineeEntity(TraineeDto qaTraineeDto) {
//        return mapper.map(qaTraineeDto, TraineeEntity.class);
//    }
//
//    public TraineeDto mapToQaTraineeDto(TraineeEntity qaTraineeEntity) {
//        return mapper.map(qaTraineeEntity, TraineeDto.class);
//    }
//
//    public TrainerEntity mapToQaTrainerEntity(TrainerDto qaTrainerDto) {
//        return mapper.map(qaTrainerDto, TrainerEntity.class);
//    }
//
//    public TrainerDto mapToQaTrainerDto(TrainerEntity qaTrainerEntity) {
//        return mapper.map(qaTrainerEntity, TrainerDto.class);
//    }
}
