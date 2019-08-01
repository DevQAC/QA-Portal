package com.qa.portal.reflection.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.*;

import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;

@RunWith(MockitoJUnitRunner.class)
public class UpdateSelfReflectionOperationTest {

	@Mock
	private ReflectionMapper reflectionMapper;

	@Mock
	private ReflectionDto reflectionDtoToUpdateFrom;

	@Mock
	private ReflectionDto reflectionDtoToUpdate;

	@Mock
	private ReflectionEntity reflectionEntityToUpdate;

	@Mock
	private ReflectionQuestionEntity reflectionQuestionEntityToUpdate1, reflectionQuestionEntityToUpdate2;

	@Mock
	private ReflectionQuestionDto reflectionQuestionDtoToUpdateFrom1, reflectionQuestionDtoToUpdateFrom2;

	private Set<ReflectionQuestionDto> reflectionQuestionDtosToUpdateFrom;

	@Mock
	private ReflectionRepository reflectionRepository;

	@Mock
	private ReflectionEntity updatedReflectionEntity;
	
	@Mock
	private ReflectionDto updatedReflectionDto;
	
	private final String USER_NAME = String.valueOf(Math.random() * Integer.MAX_VALUE), 
			TRAINER_FEEDBACK = String.valueOf(Math.random() * Integer.MAX_VALUE),
			LEARNING_PATHWAY = String.valueOf(Math.random() * Integer.MAX_VALUE), 
			STRENGTHS = String.valueOf(Math.random() * Integer.MAX_VALUE), 
			WEAKNESSES = String.valueOf(Math.random() * Integer.MAX_VALUE),
			OPPORTUNITIES = String.valueOf(Math.random() * Integer.MAX_VALUE), 
			THREATS = String.valueOf(Math.random() * Integer.MAX_VALUE);

	private final Integer RESPONSE_ONE = (int) (Math.random() * Integer.MAX_VALUE),
			RESPONSE_TWO = (int) (Math.random() * Integer.MAX_VALUE);

	@InjectMocks
	private UpdateSelfReflectionOperation operation;

	@Test
	public void updateSelfReflectionTest() {
		reflectionQuestionDtosToUpdateFrom = Set.of(reflectionQuestionDtoToUpdateFrom1,
				reflectionQuestionDtoToUpdateFrom2);
		when(reflectionRepository.findById(reflectionDtoToUpdateFrom.getId()))
				.thenReturn(Optional.of(reflectionEntityToUpdate));
		when(reflectionDtoToUpdateFrom.getFormDate()).thenReturn(LocalDate.now());
		when(reflectionDtoToUpdateFrom.getTrainerFeedback()).thenReturn(TRAINER_FEEDBACK);
		when(reflectionDtoToUpdateFrom.getLearningPathway()).thenReturn(LEARNING_PATHWAY);
		when(reflectionDtoToUpdateFrom.getStrengths()).thenReturn(STRENGTHS);
		when(reflectionDtoToUpdateFrom.getWeaknesses()).thenReturn(WEAKNESSES);
		when(reflectionDtoToUpdateFrom.getOpportunities()).thenReturn(OPPORTUNITIES);
		when(reflectionDtoToUpdateFrom.getThreats()).thenReturn(THREATS);
		when(reflectionDtoToUpdateFrom.getReflectionQuestions()).thenReturn(reflectionQuestionDtosToUpdateFrom);
		when(reflectionEntityToUpdate.getReflectionQuestions())
				.thenReturn(Set.of(reflectionQuestionEntityToUpdate1, reflectionQuestionEntityToUpdate2));
		when(reflectionQuestionDtoToUpdateFrom1.getId()).thenReturn(1);
		when(reflectionQuestionDtoToUpdateFrom2.getId()).thenReturn(2);
		when(reflectionQuestionEntityToUpdate1.getId()).thenReturn(1);
		when(reflectionQuestionEntityToUpdate2.getId()).thenReturn(2);
		when(reflectionQuestionDtoToUpdateFrom1.getResponse()).thenReturn(RESPONSE_ONE);
		when(reflectionQuestionDtoToUpdateFrom2.getResponse()).thenReturn(RESPONSE_TWO);
		when(reflectionRepository.save(reflectionEntityToUpdate)).thenReturn(updatedReflectionEntity);

		operation.updateSelfReflection(reflectionDtoToUpdateFrom, USER_NAME);

		verify(reflectionRepository, times(1)).findById(reflectionDtoToUpdateFrom.getId());
		verify(reflectionEntityToUpdate, times(1)).setFormDate(Date.valueOf(reflectionDtoToUpdateFrom.getFormDate()));
		verify(reflectionEntityToUpdate, times(1)).setTrainerFeedback(TRAINER_FEEDBACK);
		verify(reflectionEntityToUpdate, times(1)).setLearningPathway(LEARNING_PATHWAY);
		verify(reflectionEntityToUpdate, times(1)).setStrengths(STRENGTHS);
		verify(reflectionEntityToUpdate, times(1)).setWeaknesses(WEAKNESSES);
		verify(reflectionEntityToUpdate, times(1)).setOpportunities(OPPORTUNITIES);
		verify(reflectionEntityToUpdate, times(1)).setThreats(THREATS);
		verify(reflectionEntityToUpdate, times(reflectionQuestionDtosToUpdateFrom.size())).getReflectionQuestions();
		verify(reflectionDtoToUpdateFrom, times(1)).getReflectionQuestions();
		verify(reflectionQuestionEntityToUpdate1, times(1)).setResponse(RESPONSE_ONE);
		verify(reflectionQuestionEntityToUpdate2, times(1)).setResponse(RESPONSE_TWO);
		verify(reflectionRepository, times(1)).save(reflectionEntityToUpdate);
		verify(reflectionMapper, times(1)).mapToReflectionDto(updatedReflectionEntity);
	}

}
