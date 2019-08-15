package com.qa.portal.feedback.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.portal.feedback.persistence.entity.CohortCourseFeedbackEntity;

public interface CohortCourseFeedbackRepository extends JpaRepository<CohortCourseFeedbackEntity, Integer> {
	
}
