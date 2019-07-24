package com.qa.portal.reflection.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.portal.reflection.persistence.entity.ReflectionEntity;

public interface ReflectionRepository extends JpaRepository<ReflectionEntity, Integer> {

}
