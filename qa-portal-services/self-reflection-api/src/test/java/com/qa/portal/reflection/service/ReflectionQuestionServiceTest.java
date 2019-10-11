package com.qa.portal.reflection.service;

import com.qa.portal.common.persistence.repository.CohortQuestionRepository;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionQuestionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionQuestionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReflectionQuestionServiceTest {

	@Mock
	private ReflectionQuestionRepository reflectionQuestionRepo;

	@Mock
	private CohortQuestionRepository cohortQuestionRepo;

	@Mock
	private QaCohortRepository cohortRepo;
	
	@Mock
	private ReflectionQuestionMapper reflectionQuestionMapper;

	@Mock
	private ReflectionQuestionEntity reflectionQuestionEntity, savedReflectionQuestionEntity;

	@Mock
	private ReflectionQuestionDto reflectionQuestionDto;

	@Mock
	private QaSecurityContext context;

	@InjectMocks
	private ReflectionQuestionService service;
	
	private Integer REFLECTION_QUESTION_ID = 2;
	
	@Test
	public void updateReflectionQuestions() {
		setPreConditions();
		service.updateReflectionQuestions(Stream.of(reflectionQuestionDto).collect(Collectors.toSet()));
		checkPostConditionsUpdateReflectionQuestions();
	}

	@Test
	public void createReflectionQuestions() {
		setPreConditions();
		service.createReflectionQuestions(Stream.of(reflectionQuestionDto).collect(Collectors.toSet()));
		checkPostConditionsCreateReflectionQuestions();
	}

	private void setPreConditions() {
		when(reflectionQuestionDto.getId()).thenReturn(REFLECTION_QUESTION_ID);
		when(reflectionQuestionRepo.findById(REFLECTION_QUESTION_ID)).thenReturn(Optional.of(reflectionQuestionEntity));
		when(reflectionQuestionMapper.mapToReflectionQuestionEntity(reflectionQuestionDto)).thenReturn(reflectionQuestionEntity);
		when(reflectionQuestionRepo.save(reflectionQuestionEntity)).thenReturn(savedReflectionQuestionEntity);
	}
	
	private void checkPostConditionsUpdateReflectionQuestions() {
		verify(reflectionQuestionRepo).findById(REFLECTION_QUESTION_ID);
		verify(reflectionQuestionRepo).save(reflectionQuestionEntity);
		verify(reflectionQuestionMapper).mapToReflectionQuestionDto(savedReflectionQuestionEntity);
	}
	
	private void checkPostConditionsCreateReflectionQuestions() {
		verify(reflectionQuestionMapper).mapToReflectionQuestionEntity(reflectionQuestionDto);
		verify(reflectionQuestionRepo).save(reflectionQuestionEntity);
		verify(reflectionQuestionMapper).mapToReflectionQuestionDto(savedReflectionQuestionEntity);
	}
}
