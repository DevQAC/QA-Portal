package com.qa.portal.feedback.persistence.repository;

import com.qa.portal.feedback.persistence.entity.CohortCourseFeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CohortCourseFeedbackRepository extends JpaRepository<CohortCourseFeedbackEntity, Integer> {
}
