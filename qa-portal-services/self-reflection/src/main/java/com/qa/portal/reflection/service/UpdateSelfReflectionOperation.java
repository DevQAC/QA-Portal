package com.qa.portal.reflection.service;

import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateSelfReflectionOperation {

    private ReflectionRepository reflectionRepository;

    private ReflectionMapper reflectionMapper;

    @Autowired
    public UpdateSelfReflectionOperation(ReflectionRepository reflectionRepository, ReflectionMapper reflectionMapper) {
        this.reflectionRepository = reflectionRepository;
        this.reflectionMapper = reflectionMapper;
    }

    public ReflectionDto updateSelfReflection(ReflectionDto reflectionDto) {
        ReflectionEntity reflectionToUpdate = this.reflectionRepository.findById(reflectionDto.getId())
                .orElseThrow(() -> new QaResourceNotFoundException("Reflection does not exist"));
        ReflectionEntity reflectionToUpdateFrom = this.reflectionMapper.mapToReflectionEntity(reflectionDto);
        reflectionToUpdate.setFormDate(reflectionToUpdateFrom.getFormDate());
        reflectionToUpdate.setResponder(reflectionToUpdateFrom.getResponder());
        reflectionToUpdate.setReviewer(reflectionToUpdateFrom.getReviewer());
        return this.reflectionMapper.mapToReflectionDto(this.reflectionRepository.save(reflectionToUpdate));
    }
}
