package com.qa.portal.reflection.service;

import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateSelfReflectionOperationTest {

    private final String USER_NAME = "TEST_USER";

    @Mock
    private ReflectionDto reflectionDto;

    @Mock
    private ReflectionEntity savedReflectionEntity;

    @Mock
    private ReflectionEntity reflectionEntity;

    @Mock
    private ReflectionMapper reflectionMapper;

    @Mock
    private ReflectionRepository reflectionRepository;

    @InjectMocks
    private CreateSelfReflectionOperation operation;

    @Test
    public void createSelfReflection() {
        setPreConditions();
        executeAction();
        checkPostConditions();
    }

    private void setPreConditions() {
        // Return the mock reflection entity when calling mapToReflectionEntity
        when(reflectionMapper.mapToReflectionEntity(reflectionDto, USER_NAME)).thenReturn(reflectionEntity);

        // When save called on mock reflectionRepository return savedReflectionEntity
        when(reflectionRepository.save(reflectionEntity)).thenReturn(savedReflectionEntity);
    }

    private void executeAction() {
        operation.createSelfReflection(reflectionDto, USER_NAME);
    }

    private void checkPostConditions() {
        // Verifies that the mapToReflectionEntity method is called once with the reflectionDTO and USER_NAME as arguments
        verify(this.reflectionMapper, times(1))
                .mapToReflectionEntity(reflectionDto, USER_NAME);
        // Verifies that the reflectionRepository save method was called once and that the save method
        // was passed the reflection entity returned from the mapper (set up in setPreConditions)
        verify(this.reflectionRepository, times(1)).save(reflectionEntity);

        // Verify that the reflection mapper method mapToReflectionDto is called once with the savedReflectionEntity as an argument
        verify(this.reflectionMapper, times(1)).mapToReflectionDto(savedReflectionEntity);
    }
}