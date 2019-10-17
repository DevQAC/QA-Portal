package com.qa.portal.application.persistence.repository;

import com.qa.portal.application.persistence.entity.PortalProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortalProjectRepository extends JpaRepository<PortalProjectEntity, Integer> {

    Optional<PortalProjectEntity> findByName(String name);
}
