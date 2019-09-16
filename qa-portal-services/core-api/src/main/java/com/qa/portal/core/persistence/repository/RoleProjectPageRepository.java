package com.qa.portal.core.persistence.repository;

import com.qa.portal.core.persistence.entity.RoleProjectPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleProjectPageRepository extends JpaRepository<RoleProjectPageEntity, Integer> {
}
