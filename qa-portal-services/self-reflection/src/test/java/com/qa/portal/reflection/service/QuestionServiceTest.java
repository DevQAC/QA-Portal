package com.qa.portal.reflection.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import com.qa.portal.common.persistence.entity.QuestionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.persistence.entity.CohortQuestionEntity;
import com.qa.portal.common.persistence.repository.CohortQuestionRepository;
import com.qa.portal.reflection.service.mapper.QuestionMapper;

/*@RunWith(MockitoJUnitRunner.class)*/
public class QuestionServiceTest {

	@Mock
	private CohortQuestionRepository cohortQuestionRepo;

	@Mock
	private QaCohortRepository cohortRepo;

	@Mock
	private QuestionMapper mapper;

	@Mock
	private QaCohortEntity qaCohortEntity;

	@Mock
	private CohortQuestionEntity cohortQuestionEntity;

	@Mock
	private Set<CohortQuestionEntity> cohortQuestions;

	@Mock
	private QuestionEntity questionEntity;

	@InjectMocks
	private QuestionService service;

	private final Integer COHORT_ID = 1, FAKE_ID = 2;

	/*
	 * @Test public void getQuestionsForCohortTest() { setPreConditions();
	 * executeActions(); checkPostConditions(); }
	 * 
	 * @Test(expected = QaResourceNotFoundException.class) public void
	 * getQuestionsForCohortNotFoundTest() { service.getQuestionsForCohort(FAKE_ID);
	 * }
	 * 
	 * 
	 * private void setPreConditions() { cohortQuestions =
	 * Set.of(cohortQuestionEntity);
	 * when(cohortRepo.findById(COHORT_ID)).thenReturn(Optional.of(qaCohortEntity));
	 * when(cohortQuestionRepo.findByCohort(qaCohortEntity)).thenReturn(
	 * cohortQuestions);
	 * when(cohortQuestionEntity.getQuestion()).thenReturn(questionEntity); }
	 * 
	 * private void executeActions() { service.getQuestionsForCohort(COHORT_ID); }
	 * 
	 * private void checkPostConditions() { verify(cohortRepo).findById(COHORT_ID);
	 * verify(cohortQuestionRepo).findByCohort(qaCohortEntity);
	 * verify(mapper).mapToQuestionDto(questionEntity); }
	 */

}
