package com.qa.portal.reflection.service;

import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateSelfReflectionOperation {

    private ReflectionMapper reflectionMapper;

    private ReflectionRepository reflectionRepository;

    @Autowired
    public CreateSelfReflectionOperation(ReflectionMapper reflectionMapper, ReflectionRepository reflectionRepository) {
        this.reflectionMapper = reflectionMapper;
        this.reflectionRepository = reflectionRepository;
    }

    public ReflectionDto createSelfReflection(ReflectionDto reflectionDto, String userName) {
        return this.reflectionMapper
                .mapToReflectionDto(this.reflectionRepository.save(this.reflectionMapper.mapToReflectionEntity(reflectionDto, userName)));
    }
}
