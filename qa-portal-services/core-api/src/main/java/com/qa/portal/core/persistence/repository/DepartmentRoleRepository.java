package com.qa.portal.core.persistence.repository;

import com.qa.portal.core.persistence.entity.DepartmentRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRoleRepository extends JpaRepository<DepartmentRoleEntity, Integer> {
}
