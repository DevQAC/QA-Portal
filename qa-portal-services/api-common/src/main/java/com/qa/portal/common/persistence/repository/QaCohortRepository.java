package com.qa.portal.common.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.portal.common.persistence.entity.QaCohortEntity;

@Repository
public interface QaCohortRepository extends JpaRepository<QaCohortEntity, Integer> {

    Optional<QaCohortEntity> findByName(String name);
}
