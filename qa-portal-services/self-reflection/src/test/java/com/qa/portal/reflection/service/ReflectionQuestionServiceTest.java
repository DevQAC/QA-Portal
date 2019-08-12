package com.qa.portal.reflection.service;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.common.persistence.entity.CohortQuestionEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.common.persistence.repository.CohortQuestionRepository;
import com.qa.portal.reflection.persistence.repository.ReflectionQuestionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionQuestionMapper;

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
	private CohortQuestionEntity cohortQuestionEntity;
	
	@Mock
	private QaCohortEntity cohortEntity;

	@Mock
	private ReflectionQuestionEntity reflectionQuestionEntity, savedReflectionQuestionEntity;

	@Mock
	private ReflectionQuestionDto reflectionQuestionDto;

	
	@Mock
	private ReflectionQuestionEntity toUpdateFrom;

	@Mock
	private QaSecurityContext context;

	@InjectMocks
	private ReflectionQuestionService service;
	
	private Integer REFLECTION_QUESTION_ID = 2;

	@Test
	public void getReflectionQuestionsByReflectionId() {
		setPreConditions();
		service.getReflectionQuestionsByReflectionId(anyInt());
		checkPostConditions();
	}
	
	@Test
	public void updateReflectionQuestions() {
		setPreConditions();
		service.updateReflectionQuestions(Stream.of(reflectionQuestionDto).collect(Collectors.toSet()));
		checkPostConditionsUpdateReflectionQuestions();
	}

	@Test
	public void getReflectionQuestionsByCohort() {
		setPreConditions();
		service.getReflectionQuestionsByCohort(anyString());
		checkPostConditionsReflectionQuestionsByCohort();
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
		when(reflectionQuestionRepo.findByReflectionId(anyInt())).thenReturn(Stream.of(reflectionQuestionEntity).collect(Collectors.toSet()));
		when(reflectionQuestionMapper.mapToReflectionQuestionEntity(reflectionQuestionDto)).thenReturn(reflectionQuestionEntity);
		when(reflectionQuestionRepo.save(reflectionQuestionEntity)).thenReturn(savedReflectionQuestionEntity);
		when(cohortRepo.findByName(anyString())).thenReturn(Optional.of(cohortEntity));
		when(cohortQuestionRepo.findByCohort(cohortEntity)).thenReturn(Stream.of(cohortQuestionEntity).collect(Collectors.toSet()));
	}

	private void checkPostConditions() {
		verify(reflectionQuestionRepo).findByReflectionId(anyInt());
		verify(reflectionQuestionMapper).mapToReflectionQuestionDto(reflectionQuestionEntity);
	}
	
	private void checkPostConditionsUpdateReflectionQuestions() {
		verify(reflectionQuestionRepo).findById(REFLECTION_QUESTION_ID);
		verify(reflectionQuestionRepo).save(reflectionQuestionEntity);
		verify(reflectionQuestionMapper).mapToReflectionQuestionDto(savedReflectionQuestionEntity);
	}
	
	private void checkPostConditionsReflectionQuestionsByCohort() {
		verify(cohortRepo).findByName(anyString());
		verify(cohortQuestionRepo).findByCohort(cohortEntity);
	}
	
	private void checkPostConditionsCreateReflectionQuestions() {
		verify(reflectionQuestionMapper).mapToReflectionQuestionEntity(reflectionQuestionDto);
		verify(reflectionQuestionRepo).save(reflectionQuestionEntity);
		verify(reflectionQuestionMapper).mapToReflectionQuestionDto(savedReflectionQuestionEntity);
	}
}
