package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionCategoryRepository extends JpaRepository<QuestionCategoryEntity, Integer> {
    Optional<QuestionCategoryEntity> findByCategoryName(String categoryName);
}
