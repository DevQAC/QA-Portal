package com.qa.portal.core.persistence.repository;

import com.qa.portal.core.persistence.entity.DepartmentRoleApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRoleApplicationRepository extends JpaRepository<DepartmentRoleApplicationEntity, Integer> {
}
