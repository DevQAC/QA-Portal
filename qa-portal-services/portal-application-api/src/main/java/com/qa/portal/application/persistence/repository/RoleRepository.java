package com.qa.portal.application.persistence.repository;

import com.qa.portal.application.persistence.entity.PortalApplicationEntity;
import com.qa.portal.application.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(String name);

    List<RoleEntity> findByPortalApplication(PortalApplicationEntity portalApplicationEntity);
}
