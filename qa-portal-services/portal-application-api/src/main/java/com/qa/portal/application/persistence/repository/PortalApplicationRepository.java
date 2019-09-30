package com.qa.portal.application.persistence.repository;

import com.qa.portal.application.persistence.entity.PortalApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortalApplicationRepository extends JpaRepository<PortalApplicationEntity, Integer> {

    Optional<PortalApplicationEntity> findByName(String portalApplicationName);
}
