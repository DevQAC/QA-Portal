package com.qa.portal.reflection.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;

@RunWith(MockitoJUnitRunner.class)
public class GetSelfReflectionOperationTest {

	@Mock
	private ReflectionRepository reflectionRepository;
	
	@Mock
	private ReflectionMapper reflectionMapper;
	
	@Mock
	private ReflectionEntity reflectionEntity;
	
	@Mock
	private ReflectionDto reflectionDto;
	
	@Mock
	private TrainerEntity trainerEntity;
	
	@Mock
	private QaTrainerRepository trainerRepository;
	
	@InjectMocks
	private GetSelfReflectionOperation operation;

	@Test
	public void getSelfReflectionByIdTest() {
		setPreConditions();
		executeActions();
		checkPostConditions();
	}
	
	@Test(expected = QaResourceNotFoundException.class)
	public void getSelfReflectionIdNotFoundTest() {
		operation.getSelfReflectionById(anyInt());
	}
	
	@Test
	public void getSelfReflectionByUserAndDateTest() {
		setPreConditions();
		executeActionsUserAndDate();
		checkPostConditionsUserAndDate();
	}
	
	@Test(expected = QaResourceNotFoundException.class)
	public void getSelfReflectionByUserAndDateTrainerNotFoundTest() {
		operation.getSelfReflectionByUserAndDate(anyString(), LocalDate.now());
	}
	
	private void setPreConditions() {
		when(reflectionRepository.findById(anyInt())).thenReturn(Optional.of(reflectionEntity));
		when(trainerRepository.findByUserName(anyString())).thenReturn(Optional.of(trainerEntity));
		when(reflectionRepository.findByReviewerAndFormDate(any(), any())).thenReturn(Optional.of(reflectionEntity));
	}
	
	private void executeActions() {
		operation.getSelfReflectionById(anyInt());
	}

	private void checkPostConditions() {
		verify(reflectionRepository).findById(anyInt());
	}
	
	private void executeActionsUserAndDate() {
		operation.getSelfReflectionByUserAndDate(anyString(), LocalDate.now());
	}

	private void checkPostConditionsUserAndDate() {
		verify(trainerRepository).findByUserName(anyString());
		verify(reflectionRepository).findByReviewerAndFormDate(any(), any());
		verify(reflectionRepository).findByReviewerAndFormDate(any(), any());
		verify(reflectionMapper).mapToReflectionDto(reflectionEntity);
	}
}
