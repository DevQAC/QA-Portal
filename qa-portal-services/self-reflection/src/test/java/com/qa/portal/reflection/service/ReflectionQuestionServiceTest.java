package com.qa.portal.reflection.service;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.CohortQuestionEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.CohortQuestionRepository;
import com.qa.portal.reflection.persistence.repository.ReflectionQuestionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionQuestionMapper;

@RunWith(MockitoJUnitRunner.class)
public class ReflectionQuestionServiceTest {

	@Mock
	private ReflectionQuestionRepository reflectionQuestionRepo;

	@Mock
	private CohortQuestionRepository cohortQuestionRepository;

	@Mock
	private QaCohortRepository cohortRepository;
	
	@Mock
	private Set<CohortQuestionEntity> cohortQuestions;
	
	@Mock
	private CohortQuestionEntity cohortQuestion;
	
	@Mock
	private QaCohortEntity cohortEntity;

	@Mock
	private ReflectionQuestionMapper reflectionQuestionMapper;

	@Mock
	private Set<ReflectionQuestionDto> reflectionQuestionDtos;
	
	@Mock
	private ReflectionQuestionDto reflectionQuestionDto;

	@Mock
	private Set<ReflectionQuestionEntity> reflectionQuestionEntities;
	
	@Mock
	private ReflectionQuestionEntity reflectionQuestionEntity;
	
	@Mock
	private ReflectionQuestionEntity toUpdateFrom;

	@Mock
	private QaSecurityContext context;

	@InjectMocks
	private ReflectionQuestionService service;

	@Test
	public void getReflectionQuestionsByReflectionId() {
		setPreConditions();
		service.getReflectionQuestionsByReflectionId(anyInt());
		checkPostConditions();
	}
	
	@Test
	public void updateReflectionQuestions() {
		setPreConditions();
		service.updateReflectionQuestions(reflectionQuestionDtos);
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
		service.createReflectionQuestions(reflectionQuestionDtos);
		checkPostConditionsCreateReflectionQuestions();
	}

	private void setPreConditions() {
		reflectionQuestionEntities = Set.of(reflectionQuestionEntity);
		reflectionQuestionDtos = Set.of(reflectionQuestionDto);
		cohortQuestions = Set.of(cohortQuestion);
		when(reflectionQuestionRepo.findById(anyInt())).thenReturn(Optional.of(reflectionQuestionEntity));
		when(reflectionQuestionMapper.mapToReflectionQuestionEntity(reflectionQuestionDto)).thenReturn(reflectionQuestionEntity);
		when(reflectionQuestionRepo.save(reflectionQuestionEntity)).thenReturn(reflectionQuestionEntity);
		when(cohortRepository.findByname(anyString())).thenReturn(Optional.of(cohortEntity));
		when(cohortQuestionRepository.findByCohort(cohortEntity)).thenReturn(cohortQuestions);
	}

	private void checkPostConditions() {
		verify(reflectionQuestionRepo).findByReflectionId(anyInt());
	}
	
	private void checkPostConditionsUpdateReflectionQuestions() {
		verify(reflectionQuestionMapper).mapToReflectionQuestionEntity(reflectionQuestionDto);
		verify(reflectionQuestionRepo).save(reflectionQuestionEntity);
	}
	
	private void checkPostConditionsReflectionQuestionsByCohort() {
		verify(cohortRepository).findByname(anyString());
		verify(cohortQuestionRepository).findByCohort(cohortEntity);
	}
	
	private void checkPostConditionsCreateReflectionQuestions() {
		verify(reflectionQuestionMapper).mapToReflectionQuestionEntity(reflectionQuestionDto);
		verify(reflectionQuestionRepo).save(reflectionQuestionEntity);
		verify(reflectionQuestionMapper).mapToReflectionQuestionDto(reflectionQuestionEntity);
	}
}
