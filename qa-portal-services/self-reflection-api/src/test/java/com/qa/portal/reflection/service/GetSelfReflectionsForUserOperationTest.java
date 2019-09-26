package com.qa.portal.reflection.service;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GetSelfReflectionsForUserOperationTest {

	private final String USER_NAME = "TEST_USER";

	private final String UNKNOWN_NAME = "UNKNOWN_USER";

	private final Integer TRAINEE_ID = 1;

	@Mock
	private ReflectionDto reflectionDto1;

	@Mock
	private ReflectionDto reflectionDto2;

	@Mock
	private Set<ReflectionEntity> reflectionEntities;
	
	@Mock
	private ReflectionEntity re1;

	@Mock
	private ReflectionEntity re2;
	
	@Mock
	private ReflectionMapper reflectionMapper;

	@Mock
	private ReflectionRepository reflectionRepository;

	@Mock
	private QaTraineeRepository traineeRepository;
	
	@Mock
	private TraineeEntity traineeEntity;

	@InjectMocks
	private GetSelfReflectionsForUserOperation operation;
	
	@Test
	public void getSelfReflectionsForCurrentUserTest() {
		setPreConditions();
		executeAction();
		checkPostConditions();
	}
	
	@Test
	public void getSelfReflectionsForUser() {
		setPreConditions();
		executeActionForUser();
		checkPostConditionsForUser();
	}
	
	@Test(expected = QaResourceNotFoundException.class)
	public void getSelfReflectionsUserNotFound() {
		setPreConditions();
		operation.getSelfReflectionsForTrainee(UNKNOWN_NAME);
	}
	
	private void setPreConditions() {
		reflectionEntities = Stream.of(re1, re2).collect(Collectors.toSet());
		when(reflectionDto1.getFormDate()).thenReturn(LocalDate.now());
		when(reflectionDto2.getFormDate()).thenReturn(LocalDate.now());
		when(reflectionMapper.mapToReflectionDto(re1)).thenReturn(reflectionDto1);
		when(reflectionMapper.mapToReflectionDto(re2)).thenReturn(reflectionDto2);
		when(traineeRepository.findByUserName(USER_NAME)).thenReturn(Optional.of(traineeEntity));
		when(traineeRepository.findById(TRAINEE_ID)).thenReturn(Optional.of(traineeEntity));
		when(reflectionRepository.findAllByResponder(traineeEntity)).thenReturn(reflectionEntities);
	}
	
	private void executeAction() {
		operation.getSelfReflectionsForTrainee(USER_NAME);
	}
	
	private void checkPostConditions() {
		verify(traineeRepository, times(1)).findByUserName(USER_NAME);
		verify(reflectionRepository, times(1)).findAllByResponder(traineeEntity);
		verify(reflectionMapper, times(1)).mapToReflectionDto(re1);
		verify(reflectionMapper, times(1)).mapToReflectionDto(re2);
	}
	
	private void executeActionForUser() {
		operation.getSelfReflectionsForTrainee(TRAINEE_ID);
	}
	
	private void checkPostConditionsForUser() {
		verify(reflectionRepository, times(1)).findAllByResponder(traineeEntity);
		verify(reflectionMapper, times(1)).mapToReflectionDto(re1);
		verify(reflectionMapper, times(1)).mapToReflectionDto(re2);
	}
}
