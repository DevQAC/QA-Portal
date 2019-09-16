package com.qa.portal.core.persistence.repository;

import com.qa.portal.core.persistence.entity.PortalProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalProjectRepository extends JpaRepository<PortalProjectEntity, Long> {
}
