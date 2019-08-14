package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.QuestionResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionResponseRepository extends JpaRepository<QuestionResponseEntity, Integer> {
}
