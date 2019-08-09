package com.qa.portal.feedback.persistence.repository;

import com.qa.portal.feedback.persistence.entity.EvalQuestionCategoryResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvalQuestionCategoryResponseRepository extends JpaRepository<EvalQuestionCategoryResponseEntity, Integer> {
}
