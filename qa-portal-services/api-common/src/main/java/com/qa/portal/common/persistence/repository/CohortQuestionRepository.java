package com.qa.portal.common.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.entity.CohortQuestionEntity;

@Repository
@Deprecated
/**
 * TODO - To be removed once Self Reflection has been refactored to use the Question and QuestionCategory from the form-api service.
 */
public interface CohortQuestionRepository extends JpaRepository<CohortQuestionEntity, Integer> {

    Set<CohortQuestionEntity> findByCohort(QaCohortEntity cohort);
}
