package com.qa.portal.core.persistence.repository;

import com.qa.portal.core.persistence.entity.ProjectPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectPageRepository extends JpaRepository<ProjectPageEntity, Integer> {

    Optional<ProjectPageEntity> findByName(String name);
}
