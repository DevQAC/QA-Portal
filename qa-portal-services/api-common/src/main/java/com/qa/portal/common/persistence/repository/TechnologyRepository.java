package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<TechnologyEntity, Integer> {
}
