package com.qa.portal.form.services;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService {

    private FormTypeRepository formTypeRepository;

    private BaseMapper baseMapper;

    public FormService(FormTypeRepository formTypeRepository,
                       BaseMapper baseMapper) {
        this.formTypeRepository = formTypeRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public List<FormTypeDto> getForms() {
        return formTypeRepository.findAll().stream()
                .map(f -> baseMapper.mapObject(f, FormTypeDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public FormTypeDto getFormById(Integer id) {
        return formTypeRepository.findById(id)
                .map(f -> baseMapper.mapObject(f, FormTypeDto.class))
                .orElseThrow(() -> new QaPortalBusinessException("Form not found for supplied id"));
    }
}
