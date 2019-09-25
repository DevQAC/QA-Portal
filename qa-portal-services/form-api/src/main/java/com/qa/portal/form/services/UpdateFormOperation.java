package com.qa.portal.form.services;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.FormTypeEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateFormOperation {

    private FormTypeRepository formTypeRepository;

    private BaseMapper baseMapper;

    public UpdateFormOperation(FormTypeRepository formTypeRepository,
                               BaseMapper baseMapper) {
        this.formTypeRepository = formTypeRepository;
        this.baseMapper = baseMapper;
    }

    public FormTypeDto updateForm(FormTypeDto formTypeDto) {
        return getForm(formTypeDto)
                .map(form -> persistUpdatedForm(form, formTypeDto))
                .orElseThrow(() -> new QaPortalBusinessException("Form not found for supplied form name"));
    }

    private FormTypeDto persistUpdatedForm(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        formTypeEntity.setDescription(formTypeDto.getDescription());
        return baseMapper.mapObject(formTypeEntity, FormTypeDto.class);
    }

    private Optional<FormTypeEntity> getForm(FormTypeDto formTypeDto) {
        return formTypeRepository.findByFormName(formTypeDto.getFormName());
    }
}
