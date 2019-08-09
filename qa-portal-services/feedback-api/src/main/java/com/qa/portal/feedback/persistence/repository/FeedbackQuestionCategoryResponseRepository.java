package com.qa.portal.feedback.persistence.repository;

import com.qa.portal.feedback.persistence.entity.FeedbackQuestionCategoryResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackQuestionCategoryResponseRepository extends JpaRepository<FeedbackQuestionCategoryResponseEntity, Integer> {
}
