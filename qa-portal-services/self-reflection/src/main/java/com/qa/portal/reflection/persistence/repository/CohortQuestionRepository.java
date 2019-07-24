package com.qa.portal.reflection.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.portal.reflection.persistence.entity.CohortQuestionEntity;

public interface CohortQuestionRepository extends JpaRepository<CohortQuestionEntity, Integer> {

}
