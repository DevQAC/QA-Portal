package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCategoryRepository extends JpaRepository<QuestionCategoryEntity, Integer> {

}
