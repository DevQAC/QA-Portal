package com.qa.portal.reflection.service;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UpdateSelfReflectionOperationTest {

	private final String USER_NAME = "USER_NAME";

	@Mock
	private ReflectionMapper reflectionMapper;

	@Mock
	private ReflectionDto reflectionDtoToUpdateFrom;

	@Mock
	private ReflectionEntity reflectionEntityToUpdate;

	@Mock
	private ReflectionQuestionEntity reflectionQuestionEntityToUpdate1, reflectionQuestionEntityToUpdate2;

	@Mock
	private ReflectionQuestionDto reflectionQuestionDtoToUpdateFrom1, reflectionQuestionDtoToUpdateFrom2;

	@Mock
	private ReflectionRepository reflectionRepository;

	@Mock
	private ReflectionEntity updatedReflectionEntity;


	@InjectMocks
	private UpdateSelfReflectionOperation operation;

	@Test
	public void updateSelfReflectionTest() {
		setPreConditions();
		executeAction();
		checkPostConditions();
	}

	private void setPreConditions() {
		when(reflectionRepository.findById(reflectionDtoToUpdateFrom.getId()))
				.thenReturn(Optional.of(reflectionEntityToUpdate));
		when(reflectionDtoToUpdateFrom.getFormDate()).thenReturn(LocalDate.now());
		when(reflectionDtoToUpdateFrom.getReflectionQuestions())
				.thenReturn(Stream.of(reflectionQuestionDtoToUpdateFrom1, reflectionQuestionDtoToUpdateFrom2).collect(Collectors.toList()));
		when(reflectionEntityToUpdate.getReflectionQuestions())
				.thenReturn(Stream.of(reflectionQuestionEntityToUpdate1, reflectionQuestionEntityToUpdate2).collect(Collectors.toSet()));
		when(reflectionRepository.save(reflectionEntityToUpdate)).thenReturn(updatedReflectionEntity);
	}

	private void executeAction() {
		operation.updateSelfReflection(reflectionDtoToUpdateFrom, USER_NAME);
	}

	@Test(expected = QaResourceNotFoundException.class)
	public void updateSelfReflectionNotFoundTest() {
		operation.updateSelfReflection(reflectionDtoToUpdateFrom, USER_NAME);
	}

	private void checkPostConditions() {
		verify(reflectionRepository, times(1)).findById(reflectionDtoToUpdateFrom.getId());
		verify(reflectionRepository, times(1)).save(reflectionEntityToUpdate);
		verify(reflectionMapper, times(1)).mapToReflectionDto(updatedReflectionEntity);
	}
}
