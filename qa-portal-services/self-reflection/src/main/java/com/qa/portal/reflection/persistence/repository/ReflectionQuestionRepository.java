package com.qa.portal.reflection.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;

@Repository
public interface ReflectionQuestionRepository extends JpaRepository<ReflectionQuestionEntity, Integer> {

}
