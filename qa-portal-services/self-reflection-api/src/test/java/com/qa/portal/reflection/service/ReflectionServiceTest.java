package com.qa.portal.reflection.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.portal.reflection.dto.ReflectionDto;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class ReflectionServiceTest {
	
	@Mock
	private GetSelfReflectionOperation getSelfReflectionOperation;

	@Mock
    private GetSelfReflectionsForUserOperation getSelfReflectionsForUserOperation;

	@Mock
    private CreateSelfReflectionOperation createSelfReflectionOperation;

	@Mock
    private UpdateSelfReflectionOperation updateSelfReflectionOperation;

	@Mock
    private GetCohortSummaryOperation getCohortSummaryOperation;
	
	@Mock
	private ReflectionDto reflectionDto;
	
	@InjectMocks
	private ReflectionService service;

	@Test
	public void getSelfReflectionsForTraineeTest() {
		executeAction();
		checkPostConditions();
	}
	
	private void executeAction() {
		service.getSelfReflectionsForTrainee(anyString());
		service.getSelfReflectionsForTrainer(anyString());
		service.getSelfReflectionsForTrainee(anyInt());
		service.getSelfReflection(anyInt());
		service.getSelfReflection("TEST_STRING", LocalDate.now());
		service.createSelfReflection(any(), anyString());
		service.updateSelfReflection(any(), anyString());
		service.getCohortSummaryDto();
	}

	private void checkPostConditions() {
		verify(getSelfReflectionsForUserOperation).getSelfReflectionsForTrainee(anyString());
		verify(getSelfReflectionsForUserOperation).getSelfReflectionsForTrainer(anyString());
		verify(getSelfReflectionsForUserOperation).getSelfReflectionsForTrainee(anyInt());
		verify(getSelfReflectionOperation).getSelfReflectionById(anyInt());
		verify(getSelfReflectionOperation).getSelfReflectionByUserAndDate(anyString(), any(LocalDate.class));
		verify(createSelfReflectionOperation).createSelfReflection(any(), anyString());
		verify(updateSelfReflectionOperation).updateSelfReflection(any(), anyString());
		verify(getCohortSummaryOperation).getCohortSummaries();
	}

}
