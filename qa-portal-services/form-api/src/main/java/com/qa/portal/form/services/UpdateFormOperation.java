package com.qa.portal.form.services;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.FormTypeEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.form.services.mapper.FormMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateFormOperation {

    private FormTypeRepository formTypeRepository;

    private FormMapper formMapper;

    public UpdateFormOperation(FormTypeRepository formTypeRepository,
                               FormMapper formMapper) {
        this.formTypeRepository = formTypeRepository;
        this.formMapper = formMapper;
    }

    public FormTypeDto updateForm(FormTypeDto formTypeDto) {
        return getForm(formTypeDto)
                .map(form -> persistUpdatedForm(form, formTypeDto))
                .orElseThrow(() -> new QaPortalBusinessException("Form not found for supplied form name"));
    }

    private FormTypeDto persistUpdatedForm(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        formMapper.updateExistingFormTypeEntity(formTypeEntity, formTypeDto);
        return formMapper.createFormDto(formTypeRepository.save(formTypeEntity));
    }

    private Optional<FormTypeEntity> getForm(FormTypeDto formTypeDto) {
        return formTypeRepository.findById(formTypeDto.getId());
    }
}
