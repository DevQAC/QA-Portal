package com.qa.portal.form.services;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.FormTypeEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateFormOperation {

    private FormTypeRepository formTypeRepository;

    private BaseMapper baseMapper;

    public CreateFormOperation(FormTypeRepository formTypeRepository,
                               BaseMapper baseMapper) {
        this.formTypeRepository = formTypeRepository;
        this.baseMapper = baseMapper;
    }

    public FormTypeDto createForm(FormTypeDto formTypeDto) {
        if (formExists(formTypeDto)) {
            throw new QaPortalBusinessException("Form already exists with supplied name");
        }

        FormTypeEntity formTypeEntity = baseMapper.mapObject(formTypeDto, FormTypeEntity.class);
        FormTypeEntity savedForm = formTypeRepository.save(formTypeEntity);
        return baseMapper.mapObject(savedForm, FormTypeDto.class);
    }

    private boolean formExists(FormTypeDto formTypeDto) {
        return formTypeRepository.findByFormName(formTypeDto.getFormName())
                .map(f -> true)
                .orElseGet(() -> false);
    }
}
