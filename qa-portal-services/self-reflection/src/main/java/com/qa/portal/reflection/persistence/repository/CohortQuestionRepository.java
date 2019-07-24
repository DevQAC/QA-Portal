package com.qa.portal.reflection.persistence.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.reflection.persistence.entity.CohortQuestionEntity;

public interface CohortQuestionRepository extends JpaRepository<CohortQuestionEntity, Integer> {

	public Set<CohortQuestionEntity> findByCohort(QaCohortEntity cohort);
}
