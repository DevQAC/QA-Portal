package com.qa.portal.reflection.persistence.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;

@Repository
public interface ReflectionQuestionRepository extends JpaRepository<ReflectionQuestionEntity, Integer> {
    Set<ReflectionQuestionEntity> findByReflectionId(Integer id);

    List<ReflectionQuestionEntity> findAllByReflection(ReflectionEntity reflection);
}
