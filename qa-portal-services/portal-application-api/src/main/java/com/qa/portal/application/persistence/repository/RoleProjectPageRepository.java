package com.qa.portal.application.persistence.repository;

import com.qa.portal.application.persistence.entity.RoleProjectPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleProjectPageRepository extends JpaRepository<RoleProjectPageEntity, Integer> {
}
