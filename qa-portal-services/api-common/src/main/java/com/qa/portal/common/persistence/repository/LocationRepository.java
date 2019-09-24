package com.qa.portal.common.persistence.repository;

import com.qa.portal.common.persistence.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

    Optional<LocationEntity> findByName(String name);
}
