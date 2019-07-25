package com.qa.portal.common.util.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.qa.portal.common.dto.QaTraineeDto;
import com.qa.portal.common.dto.QaTrainerDto;
import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.persistence.entity.QaUserEntity;
import com.qa.portal.common.persistence.entity.QaTraineeEntity;
import com.qa.portal.common.persistence.entity.QaTrainerEntity;

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
	
	public QaTraineeEntity mapToQaTraineeEntity(QaTraineeDto qaTraineeDto) {
		return mapper.map(qaTraineeDto, QaTraineeEntity.class);
	}
	
	public QaTraineeDto mapToQaTraineeDto(QaTraineeEntity qaTraineeEntity) {
		return mapper.map(qaTraineeEntity, QaTraineeDto.class);
	}
	
	public QaTrainerEntity mapToQaTrainerEntity(QaTrainerDto qaTrainerDto) {
		return mapper.map(qaTrainerDto, QaTrainerEntity.class);
	}
	
	public QaTrainerDto mapToQaTrainerDto(QaTrainerEntity qaTrainerEntity) {
		return mapper.map(qaTrainerEntity, QaTrainerDto.class);
	}

}
