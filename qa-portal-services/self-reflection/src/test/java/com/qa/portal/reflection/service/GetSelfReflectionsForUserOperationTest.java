package com.qa.portal.reflection.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;

@RunWith(MockitoJUnitRunner.class)
public class GetSelfReflectionsForUserOperationTest {

	@Mock
	private ReflectionDto reflectionDto;

	@Mock
	private Set<ReflectionEntity> reflectionEntities;
	
	@Mock
	private ReflectionEntity re1, re2;
	
	@Mock
	private ReflectionMapper reflectionMapper;

	@Mock
	private ReflectionRepository reflectionRepository;

	@Mock
	private QaTraineeRepository traineeRepository;
	
	@Mock
	private TraineeEntity traineeEntity;

	private final String USER_NAME = "TEST_USER";
	
	private final String UNKNOWN_NAME = "UNKNOWN_USER";
	
	private final Integer TRAINEE_ID = 1;

	@InjectMocks
	private GetSelfReflectionsForUserOperation operation;
	
	@Test
	public void getSelfReflectionsForCurrentUserTest() {
		setPreConditions();
		executeAction();
		checkPostConditions();
	}
	
	@Test
	public void getSelfReflectionsForUserIdTest() {
		setPreConditions();
		executeActionUserId();
		checkPostConditionsUserId();
	}
	
	@Test(expected = QaResourceNotFoundException.class)
	public void getSelfReflectionsUserNotFound() {
		operation.getSelfReflectionsForTrainee(UNKNOWN_NAME);
	}
	
	private void setPreConditions() {
		when(traineeRepository.findByUserName(USER_NAME)).thenReturn(Optional.of(traineeEntity));
		when(reflectionRepository.findByResponderId(traineeEntity.getId())).thenReturn(reflectionEntities);
		when(reflectionRepository.findByResponderId(TRAINEE_ID)).thenReturn(reflectionEntities);
		when(reflectionEntities.stream()).thenReturn(Stream.of(re1, re2));
	}
	
	private void executeAction() {
		operation.getSelfReflectionsForTrainee(USER_NAME);
	}
	
	private void checkPostConditions() {
		verify(traineeRepository, times(1)).findByUserName(USER_NAME);
		verify(reflectionRepository, times(1)).findByResponderId(traineeEntity.getId());
		verify(reflectionMapper, times(1)).mapToReflectionDto(re1);
		verify(reflectionMapper, times(1)).mapToReflectionDto(re2);
	}
	
	private void executeActionUserId() {
		operation.getSelfReflectionsForUser(TRAINEE_ID);
	}
	
	private void checkPostConditionsUserId() {
		verify(reflectionRepository, times(1)).findByResponderId(TRAINEE_ID);
		verify(reflectionMapper, times(1)).mapToReflectionDto(re1);
		verify(reflectionMapper, times(1)).mapToReflectionDto(re2);
	}
}
