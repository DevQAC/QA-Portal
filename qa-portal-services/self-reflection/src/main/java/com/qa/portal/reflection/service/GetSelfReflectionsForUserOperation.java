package com.qa.portal.reflection.service;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.QaTraineeEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;

@Component
public class GetSelfReflectionsForUserOperation {
	
	public Set<ReflectionDto> getSelfReflectionsForUser(String traineeId, ReflectionRepository reflectionRepo, QaTraineeRepository traineeRepo) {
		QaTraineeEntity trainee = traineeRepo.findByUserName(traineeId).stream().findFirst()
				.orElseThrow(() -> new QaResourceNotFoundException("Trainee does not exist"));
		reflectionRepo.findByResponder(trainee);
		return null;
	}

}
