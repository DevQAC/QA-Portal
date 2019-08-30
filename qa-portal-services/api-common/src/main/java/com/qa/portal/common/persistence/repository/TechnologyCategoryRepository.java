package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.TechnologyCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyCategoryRepository extends JpaRepository<TechnologyCategoryEntity, Integer> {
}
